package com.jasbir.movie.ui.View.UserInterface

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jasbir.movie.data.dataclass.MoviedetailResponse
import com.jasbir.movie.data.dataclass.TrailerResponse
import com.jasbir.movie.R
import com.jasbir.movie.ui.View.Adapter.Castadapter
import com.jasbir.movie.ui.View.Adapter.GenricAdapter
import com.jasbir.movie.ui.ViewModel.DetailViewmodel
import com.jasbir.movie.util.GlideClass
import io.reactivex.disposables.CompositeDisposable
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
        backbtn.setOnClickListener {
            onBackPressed()
        }


    }




}