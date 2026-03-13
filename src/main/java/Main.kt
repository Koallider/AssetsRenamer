import java.io.File
import java.io.IOException
import java.util.UUID

const val path = "assets/"

const val filename = "data.json"
const val outputFilename = "out.json"

val imageMap = HashMap<String, String>()

fun main() {

    val outputFIle = File(path + outputFilename).printWriter()

    var out: String

    File(path + filename).forEachLine {

        val nameRegex = "[a-zA-Z\\d-_\\s]*\\.[pP][nN][gG]".toRegex()
        val pathRegex = """"[a-zA-Z\d-_\s\/]*\.[pP][nN][gG]"""".toRegex()
        val nameMatch = nameRegex.find(it)
        val pathMatch = pathRegex.find(it)
        out = it
        if(nameMatch != null && pathMatch != null){

            val pathMatchValue = pathMatch.value.replace("\"", "")

            val newPath: String?
            if(imageMap[pathMatchValue] == null){
                newPath = pathMatchValue.replace(nameMatch.value, UUID.randomUUID().toString())
                imageMap[pathMatchValue] = newPath
            }else{
                newPath = imageMap[pathMatchValue]
            }
            out = it.replace(pathMatchValue, newPath!!)
        }
        outputFIle.write(out + "\n")
    }
    outputFIle.flush()
    imageMap.forEach { (k, v) ->
        try {
            val src = File(path + k)
            if (!src.exists()) {
                println("File doesn't exist ${k}")
            }

            val dest = File(path + v)
            if (dest.exists()) {
                println("Destination file already exist ${v}")
            }
            src.renameTo(dest)
        } catch (e: IOException) {
            println("error")
        }
    }
}