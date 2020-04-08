import com.xenomachina.argparser.ArgParser

class MyArgs(parser: ArgParser) {
    val email by parser.storing("-E", "--email", help = "E-mail of the user's account")
    val password by parser.storing("-P", "--password", help = "Users account's password (spaces or funny symbols can fail!)")
}
