import android.util.Log
import com.sayhitoiot.desafiomobile2you.BuildConfig
import com.sayhitoiot.desafiomobile2you.api.ApiTMDB

class ApiManager : InteractApi {


    private var service: ApiTMDB

    companion object {
        const val TAG = "data-manager"
    }

    init {

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(ApiTMDB::class.java)

    }

    override fun getMoviesDetails(callbackMoviesDetails: OnGetMoviesDetailsCallback) {
        service.getMovieDetails(id = ID_ANGRY_BIRDS, apiKey = API_KEY, language = PORTUGUES)
                .enqueue(object : Callback<Movie> {
                    override fun onResponse(call: Call<Movie>, response: Response<Movie> ) {
                        response.body()?.let { callbackMoviesDetails.onSuccess(it) }
                        Log.d(TAG, "response: ${response.body()} ")
                    }

                    override fun onFailure(call: Call<Movie>, t: Throwable) {
                        callbackMoviesDetails.onError()
                        t.printStackTrace()
                        Log.d(TAG, t.toString())
                    }
                })
    }

    override fun getSimilarMovies(callbackSimilarMovies: OnGetSimilarMoviesDetailsCallback) {
        service.getSimilarMovies(movieId = ID_ANGRY_BIRDS, apiKey = API_KEY, language = PORTUGUES)
                .enqueue(object : Callback<SimilarMovies> {
                    override fun onResponse(
                            call: Call<SimilarMovies>,
                            response: Response<SimilarMovies>
                    ) {
                        response.body()?.let { callbackSimilarMovies.onSuccess(it) }
                    }

                    override fun onFailure(call: Call<SimilarMovies>, t: Throwable) {
                        callbackSimilarMovies.onError()
                        t.printStackTrace()
                        Log.d(TAG, t.toString())
                    }
                })
    }
}