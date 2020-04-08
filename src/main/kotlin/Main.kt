import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody

var ACCOUNT_EMAIL: String = ""
var ACCOUNT_PASSWORD: String = ""

/** run with arguments
 * -E email -P password
 */
fun main(args: Array<String>) = mainBody {
    ArgParser(args).parseInto(::MyArgs).run {
        ACCOUNT_EMAIL = this.email
        ACCOUNT_PASSWORD = this.password
        login()
        purge()
    }

}
