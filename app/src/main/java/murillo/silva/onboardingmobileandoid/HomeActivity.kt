package murillo.silva.onboardingmobileandoid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    private fun navigatetoHome(){
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }

}