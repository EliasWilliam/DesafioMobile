interface InteractApi {
    fun getMoviesDetails(callbackMoviesDetails: OnGetMoviesDetailsCallback)
    fun getSimilarMovies(callbackSimilarMovies: OnGetSimilarMoviesDetailsCallback)
}