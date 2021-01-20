package com.jasbir.flix.View.UserInterface

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.creativaties.modelfactory.Networking.Networking
import com.jasbir.flix.Model.dataclass.MoviedetailResponse
import com.jasbir.flix.Model.dataclass.TrailerResponse
import com.jasbir.flix.R
import com.jasbir.flix.View.Adapter.Castadapter
import com.jasbir.flix.View.Adapter.GenricAdapter
import com.jasbir.flix.ViewModel.DetailViewmodel
import com.jasbir.flix.util.GlideClass
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import timber.log.Timber


class DetailActivity : AppCompatActivity() {
    private lateinit var disposable: CompositeDisposable
    private lateinit var mlist: ArrayList<String>
    private lateinit var glist: List<MoviedetailResponse.Genre>
    private lateinit var trailerList: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        trailerList= ArrayList()

        mlist = ArrayList()
        Timber.plant(Timber.DebugTree())
        disposable = CompositeDisposable()
        Timber.plant(Timber.DebugTree())
        val movieId: String = intent.getStringExtra("id").toString()


        val viewmodel = ViewModelProvider(this).get(DetailViewmodel::class.java)





        viewmodel.setId(movieId)


        viewmodel.invokeApi()
        viewmodel.responseDetail.observe(this, Observer {

            var image = it.backdrop_path.toString()
            var resultImage = "https://image.tmdb.org/t/p/original/$image"
            GlideClass().setBackgroundImage(this, resultImage, rootDetail)


            detailTitle.text = it.title
            getInfo.text = it.overview
            glist = it.genres

            for (glists in glist) {
                mlist.add(glists.name)
            }


            rv_genric.apply {
                layoutManager =
                    LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
                adapter = GenricAdapter(mlist)

            }

        })

        viewmodel.responseCast.observe(this, Observer {

            rv_cast.apply {
                layoutManager =
                    LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = Castadapter(it.cast)

            }
        })

        trailerWrapper.setOnClickListener {view->

            viewmodel.responseReview.observe(this, Observer {

                var  tList : List<TrailerResponse.Result> =it.results
                for(tLists in tList){
                    trailerList.add(tLists.key)
                }

                if(trailerList[0] != null){
                    var t = trailerList[0]
                    startActivity(Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=$t")))

                }else{
                    Toast.makeText(this,"No Trailer Available",Toast.LENGTH_LONG).show()
                }





            })
        }


    }




}