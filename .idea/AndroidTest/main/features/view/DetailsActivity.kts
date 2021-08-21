class DetailsActivity : AppCompatActivity(),
        DetailsViewToPresenter {

    private val presenter: DetailsPresenterToView by lazy {
        DetailsPresenter(this)
    }
    private val adapter: SimilarMoviesAdapter by lazy {
        SimilarMoviesAdapter(mutableListOf())
    }

    override val context: Context get() = this
    private var recyclerView: RecyclerView? = null
    private var imageMovie: ImageView? = null
    private var imageLike: ImageView? = null
    private var textLikes: TextView? = null
    private var textViews: TextView? = null
    private var textError: TextView? = null
    private var imageError: ImageView? = null
    private var imageBack: ImageView? = null
    private var buttonUpdate: MaterialButton? = null
    private var appBar: AppBarLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)
        presenter.onCreate()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun initializeViews() {
        appBar = appBarLayout
        recyclerView = detailsActivity_recyclerView_similarMovies
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter
        imageMovie = detailsActivity_imageView_background
        imageLike = detailsActivity_imageView_likes
        imageLike?.setOnClickListener { presenter.imageLikeTapped() }
        imageBack = detailsActivity_imageView_back
        imageBack?.setOnClickListener { onBackPressed() }
        textLikes = detailsActivity_textView_likes
        textViews = detailsActivity_textView_popularity
        textError = detailsActivity_textView_error
        imageError = detailsActivity_imageView_error
        buttonUpdate = detailsActivity_materialButton_update
        buttonUpdate?.setOnClickListener { presenter.onResume() }
    }

    override fun showDetailsOfMovie(path: String, likes: String, views: String) {
        imageMovie?.let {
            Picasso
                    .get()
                    .load(path)
                    .fit()
                    .error(R.drawable.ic_launcher_background)
                    .into(it)
        }
        textLikes?.text = likes
        textViews?.text = views
        Log.d(TAG, path)
    }

    override fun showDetailsOfMovieWithError() {
        textError?.visibility = VISIBLE
        imageError?.visibility = VISIBLE
        buttonUpdate?.visibility = VISIBLE
        appBar?.visibility = GONE
        recyclerView?.visibility = GONE
    }

    override fun postSimilarMovies(similarMovies: MutableList<SimilarMovie>) {
        appBar?.visibility = VISIBLE
        recyclerView?.visibility = VISIBLE
        imageError?.visibility = GONE
        textError?.visibility = GONE
        buttonUpdate?.visibility = GONE
        recyclerView?.setItemViewCacheSize(similarMovies.size)
        recyclerView?.visibility = VISIBLE
        adapter.updateList(similarMovies)
    }

    override fun renderImageLikeFill() {
        imageLike?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp))
    }

    override fun renderImageLikeDefault() {
        imageLike?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp))
    }

    override fun updateLikes(likes: String) {
        textLikes?.text = likes
    }

    companion object {
        const val TAG = "details-activity"
    }

}
