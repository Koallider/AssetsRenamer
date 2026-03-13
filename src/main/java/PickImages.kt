import java.io.File
import java.io.IOException
import java.util.UUID



fun main() {

    val path = "assets/"

    val filename = "data.json"

    val imageSet = HashSet<String>()

    File(path + filename).forEachLine {

        val pathRegex = """"[a-zA-Z\d-_\/]*\.[pP][nN][gG]"""".toRegex()
        val pathMatch = pathRegex.find(it)
        if(pathMatch != null){
            imageSet.add(pathMatch.value.replace("\"", ""))
        }
    }
    imageSet.forEach { imagePath ->
        try {
            val src = File(path + imagePath)
            if (!src.exists()) {
                println("File doesn't exist at path: $path$imagePath")
            }

            src.copyTo(File(path + "result/" + imagePath), overwrite = true)
            //src.delete()
        } catch (e: IOException) {
            println("error")
        }
    }
}