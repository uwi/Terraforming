object SimpleScalaAI {
  def main(args: Array[String]) {
    var line = ""
    var first = false

    while ({ line = readLine(); line ne null }) {
      while ( line != "EOS" ) {
        line = readLine()
      }

      if (first) {
        first = false
        System.out.println("SimpleScalaAI")
      }
      else {
        System.out.println("finish")
      }
      System.out.println("")
    }
  }
}