package com.capstone.cuacatani.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.capstone.cuacatani.R
import com.capstone.cuacatani.ViewModelFactory
import com.capstone.cuacatani.data.pref.UserPreference
import com.capstone.cuacatani.databinding.ActivityMainBinding
import com.capstone.cuacatani.model.WeatherApp
import com.capstone.cuacatani.network.WeatherService
import com.capstone.cuacatani.response.LoginResult
import com.capstone.cuacatani.ui.about.AboutActivity
import com.capstone.cuacatani.ui.fragment.CornFragment
import com.capstone.cuacatani.ui.fragment.FruitFragment
import com.capstone.cuacatani.ui.fragment.VegFragment
import com.capstone.cuacatani.ui.plants.PlantsActivity
import com.capstone.cuacatani.ui.predict.PredictActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//211dec237321e8df95005da2c4b2976f

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var userPreference: UserPreference
    private lateinit var tabLayout: TabLayout
    private lateinit var bottomNavigationView: BottomNavigationView

//    private lateinit var resView: TextView
//    private lateinit var interpreter: Interpreter
//    private lateinit var interpreter1: Interpreter
//    private lateinit var interpreter2: Interpreter
//    private val modelFilename="model_cluster0_tflite.tflite"
//    private val modelFilename1="model_cluster1_tflite.tflite"
//    private val modelFilename2="model_cluster2_tflite.tflite"

    private val cornFragment = CornFragment()
    private val vegFragment = VegFragment()
    private val fruitFragment = FruitFragment()

//    private val classIndexToLabel = mapOf(
//        0 to "Jagung",
//        1 to "Kacang Panjang",
//        2 to "Lentil Hitam",
//        3 to "Lentil",
//        4 to "Mangga",
//        5 to "Kopi",
//        6 to "Kacang Polong",
//        // Add more entries as needed
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        resView = binding.tvYourPredict

        binding.user.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

//        interpreter = Interpreter(loadModelFile(modelFilename))
//        interpreter1 = Interpreter(loadModelFile(modelFilename1))
//        interpreter2 = Interpreter(loadModelFile(modelFilename2))

        userPreference = UserPreference(this)
        setupUserInfo()
        handleLayout()
        supportActionBar?.hide()
        setDefaultTab(CornFragment())
        setOnTabClickedListener(CornFragment())

        fetchWeatherData("Surabaya")
        searchCity()
    }

    private fun loadModelFile(modelFilename: String): ByteBuffer {
        val assetManager = assets
        val inputStream = assetManager.open(modelFilename)
        val modelBuffer = inputStream.readBytes()

        val byteBuffer = ByteBuffer.allocateDirect(modelBuffer.size)
        byteBuffer.order(ByteOrder.nativeOrder())
        byteBuffer.put(modelBuffer)
        byteBuffer.flip()

        return byteBuffer
    }

    private fun setupUserInfo(){
        val loginResult: LoginResult? = userPreference.gainUser()
        if (loginResult != null) {
            binding.tvNames.text = loginResult.name
        } else {
            binding.tvNames.text = "Please Login"
        }
    }

    private fun handleLayout(){
        bindUIComponents()
        bottomNavigationView.selectedItemId = R.id.navigation_home

        handleTabButtonPress()
        tabLayout = binding.tabs
        tabLayout.addTab(tabLayout.newTab().setText("Corn"))
        tabLayout.addTab(tabLayout.newTab().setText("Vegetable"))
        tabLayout.addTab(tabLayout.newTab().setText("Fruit"))

    }

    private fun searchCity(){
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    fetchWeatherData(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

//    private fun runInferenceForWeatherPrediction(temperature: Float, humidity: Float) {
//        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 2), DataType.FLOAT32)
//        inputFeature0.loadArray(floatArrayOf(temperature, humidity))
//        val inputFeature1 = TensorBuffer.createFixedSize(intArrayOf(1, 2), DataType.FLOAT32)
//        inputFeature1.loadArray(floatArrayOf(temperature, humidity))
//        val inputFeature2 = TensorBuffer.createFixedSize(intArrayOf(1, 2), DataType.FLOAT32)
//        inputFeature2.loadArray(floatArrayOf(temperature, humidity))
//
//        val outputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 7), DataType.FLOAT32)
//        val outputFeature1 = TensorBuffer.createFixedSize(intArrayOf(1, 9), DataType.FLOAT32)
//        val outputFeature2 = TensorBuffer.createFixedSize(intArrayOf(1, 3), DataType.FLOAT32)
//
//        // Run the inference
//        interpreter.run(inputFeature0.buffer, outputFeature0.buffer)
//        interpreter1.run(inputFeature1.buffer, outputFeature1.buffer)
//        interpreter2.run(inputFeature2.buffer, outputFeature2.buffer)
//
//        // Process the model output as needed
//        val predictionResult = outputFeature0.floatArray
//        val predictionResult1 = outputFeature1.floatArray
//        val predictionResult2 = outputFeature2.floatArray
//
//        // Decode the predicted class
//        val maxProbability = predictionResult.maxOrNull() ?: 0.0
//        val predictedClassIndex = predictionResult.indexOfFirst { it == maxProbability }
////        val predictedClassLabel = decodeLabel(predictedClassIndex)
//
//        val maxProbability1 = predictionResult1.maxOrNull() ?: 0.0
//        val predictedClassIndex1 = predictionResult1.indexOfFirst { it == maxProbability1 }
////        val predictedClassLabel1 = decodeLabel(predictedClassIndex1)
//
//        val maxProbability2 = predictionResult2.maxOrNull() ?: 0.0
//        val predictedClassIndex2 = predictionResult2.indexOfFirst { it == maxProbability2 }
////        val predictedClassLabel2 = decodeLabel(predictedClassIndex2)
//
//        val result = predictedClassIndex
//
//        // Display the result in the 'resView' TextView
////        val resultText = "$predictedClassLabel, $predictedClassLabel1, $predictedClassLabel2"
//
////        resView.text =resultText
//        interpreter.close()
//        interpreter1.close()
//        interpreter1.close()
//    }


//    private fun decodeLabel(classIndex: Int): String {
//        // Use the mapping to get the label
//        return classIndexToLabel[classIndex] ?: "Unknown Label"
//    }


    private fun fetchWeatherData(cityName: String) {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build().create(WeatherService::class.java)

        val response = retrofit.getWeather(cityName, "211dec237321e8df95005da2c4b2976f", "metric")
        response.enqueue(object : Callback<WeatherApp> {
            override fun onResponse(call: Call<WeatherApp>, response: Response<WeatherApp>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    val temperature = responseBody.main.temp.toFloat()
                    val decimalFormat = DecimalFormat("#")
                    val formattedTemperature = decimalFormat.format(temperature)
                    val pressure = responseBody.main.pressure
                    val wind = responseBody.wind.speed
                    val humidity = responseBody.main.humidity.toFloat()
                    val condition = responseBody.weather.firstOrNull()?.main ?: "unknown"

                    binding.tvSuhu.text = formattedTemperature
                    binding.tvCondt.text = condition
                    binding.tvPressure2.text = "$pressure km/h"
                    binding.tvWind2.text = "$wind m/s"
                    binding.tvHumadity2.text = "$humidity %"
                    binding.tvDay.text = dayName(System.currentTimeMillis())
                    binding.tvDate.text = date()
                    binding.city.text = "$cityName"

                    setWeatherImage(condition)
//                    runInferenceForWeatherPrediction(temperature, humidity)
//                    Log.d("TAG", "onResponse: $temperature")
                }
            }

            override fun onFailure(call: Call<WeatherApp>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Error fetching weather data",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })
    }

    private fun setWeatherImage(condition: String) {
        val imageResId: Int = when (condition.toLowerCase(Locale.getDefault())) {
            "clouds" -> R.drawable.cloudy
            "clear" -> R.drawable.sunny
            "sunny" -> R.drawable.sunny
            "rain" -> R.drawable.rain
            "snow" -> R.drawable.snow
            "haze" -> R.drawable.haze
            else -> R.drawable.sunny
        }

        binding.ivAwan.setImageResource(imageResId)
    }

    private fun date(): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format((Date()))
    }

    fun dayName(timestamp: Long): String {
        val sdf = SimpleDateFormat("EEE, ", Locale.getDefault())
        return sdf.format((Date()))
    }

    private fun bindUIComponents() {
        bottomNavigationView = findViewById(R.id.nav_view)
    }

    private fun handleTabButtonPress() {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_predict -> {
                    // Get the current city name from the binding
                    val cityName = binding.city.text.toString()

                    // Start PredictActivity with the current city name
                    val intent = Intent(applicationContext, PredictActivity::class.java)
                    intent.putExtra("CITY_NAME", cityName)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_plant -> {
                    loadActivities(PlantsActivity())
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_about -> {
                    loadActivities(AboutActivity())
                    return@setOnItemSelectedListener true
                }

            }
            return@setOnItemSelectedListener false
        }
    }

    private fun loadActivities(activity: AppCompatActivity) {
        val intent = Intent(applicationContext, activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        finish()
    }

    private fun setDefaultTab(fragment: CornFragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commitNow()
    }

    private fun setOnTabClickedListener(fragment: CornFragment) {
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val selectedFragment = when (tab?.position) {
                    0 -> cornFragment
                    1 -> vegFragment
                    2 -> fruitFragment
                    else -> cornFragment
                }

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
}