

object firstdemo {

  def main(args: Array[String]): Unit ={
    import com.typesafe.config.{Config, ConfigFactory}
    val defaultConfig = ConfigFactory.parseResources("defaults.conf")

    println(defaultConfig.getString("conf.appname"))


 /*   val conf= new SparkConf().setMaster("local[*]").setAppName("Demo")
    val sc=new SparkContext(conf)
    val rdd = sc.parallelize(Array(5,10,30))


    println(rdd.reduce(_+_))*/


  }

}
