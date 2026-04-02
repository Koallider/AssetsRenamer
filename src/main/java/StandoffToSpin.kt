import java.io.File
import java.io.IOException
import java.util.UUID
import kotlin.collections.component1
import kotlin.collections.component2


fun main() {

    val outputFIle = File(path + outputFilename).printWriter()
    var out: String

    File(path + filename).forEachLine {
        if(it.contains("dropRateId") || it.contains("caseId")){
            out = ""
        } else if(it.matches("\\s*\"name\": \"(.+?)\",".toRegex())) {
            val nameRegex = "\\s*\"name\": \"(.+?)\",".toRegex()
            val matchResult = nameRegex.find(it)
            val caseName = matchResult?.groups?.get(1)?.value ?: ""
            out = "$it\n \"key\": \"$caseName\",\n"
            outputFIle.write(out + "\n")
        } else {
            out = it.replace(oldValue = "\"caseName\"", newValue = "\"name\"")
            out = out.replace(oldValue = "\"collectionName\"", newValue = "\"collection\"")
            out = out.replace(oldValue = "\"casePrice\"", newValue = "\"price\"")
            out = out.replace(oldValue = "\"caseImg\"", newValue = "\"image\"")
            out = out.replace(oldValue = "\"shopPromoImage\"", newValue = "\"promoIcon\"")
            out = out.replace(oldValue = "\"caseSpinBackground\"", newValue = "\"collectionBackground\"")
            out = out.replace(oldValue = "\"dropList\"", newValue = "\"rarityList\"")
            out = out.replace(oldValue = "\"itemPrice\"", newValue = "\"price\"")
            out = out.replace(oldValue = "\"stattrackImg\"", newValue = "\"stattrackImage\"")
            out = out.replace(oldValue = "\"skinImg\"", newValue = "\"image\"")
            out = out.replace(oldValue = "\"rate\"", newValue = "\"dropChance\"")
            outputFIle.write(out + "\n")
        }
    }
    outputFIle.flush()
}