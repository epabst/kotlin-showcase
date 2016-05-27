package complex

import java.util.*

/**
 * The model for working with a certain kind of stored entities.
 * @author Eric Pabst (epabst@gmail.com)
 * Date: 5/25/16
 * Time: 3:53 PM
 */
interface Repository<T> {
    val list: List<T>

    fun save(original: T?, replacement: T)

    fun remove(item: T)
}

object Factory {
    val cityRepository: Repository<City> = if (true) CityLocalStorageRepository else CityInMemoryRepository
}

object CityInMemoryRepository : Repository<City> {
    override val list = arrayListOf(
            City("Prague", Continent.EUROPE),
            City("London", Continent.EUROPE),
            City("New York", Continent.AMERICA))

    override fun save(original: City?, replacement: City) {
        if (original != null) {
            list.remove(original)
        }
        list.add(replacement)
    }

    override fun remove(item: City) {
        list.remove(item)
    }
}

object CityLocalStorageRepository : Repository<City> {
    private val defaultList: List<City> = arrayListOf(
            City("Paris", Continent.EUROPE),
            City("London", Continent.EUROPE),
            City("San Francisco", Continent.AMERICA))

    override val list: ArrayList<City> by lazy {
        val cityListString = kotlin.browser.localStorage.getItem("cityList")
        if (cityListString != null) {
            try {
                println(cityListString)
                val cityArray = JSON.parse<Array<CityJS>>(cityListString)
                ArrayList(cityArray.map { City(it) })
            } catch (t: Throwable) {
                println("Throwable: " + t)
                ArrayList(defaultList)
            }
        }
        else {
            ArrayList(defaultList)
        }
    }

    override fun save(original: City?, replacement: City) {
        if (original != null) {
            list.remove(original)
        }
        list.add(replacement)
        store()
    }

    override fun remove(item: City) {
        list.remove(item)
        store()
    }


    private fun store() {
        kotlin.browser.localStorage.setItem("cityList", JSON.stringify(list))
    }
}
