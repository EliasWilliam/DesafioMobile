enum class ToGenres constructor(val value : Int)
{

    Ação(28),
    Aventura(10),
    Animação(12),
    Comédia(35),
    Crime(80),
    Documentário(80),
    Drama(18),
    Fantasia(14),
    História(36),
    Horror(27),
    Mistério(745),
    Romance(10749),
    Ficção_Científica(459),
    Suspense(53),
    Guerra(752);


    companion object {
        operator fun invoke(rawValue: Int) = values().find {
            it.value == rawValue
        }.toString().replace("_", " ")
    }

}