package murillo.silva.onboardingmobileandoid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyCallback
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainer : LinearLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnboartingItms()
        setupIndicators()
        setCurrentIndicator(0)
    }

    private fun setOnboartingItms(){
        onboardingItemsAdapter = OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                    onboardingImage = R.drawable.space,
                    title = "Tenha controle",
                    description = "Organiza suas tarefas e tenha mais controles ao fazer ela"

                ),
                OnboardingItem(
                    onboardingImage =  R.drawable.exploration,
                    title = "Explore. Viva",
                    description = "Tenha uma exploração mais aconchegante. Aproveite a vida ao usar o celular"

                ),
                OnboardingItem(
                    onboardingImage = R.drawable.fred,
                    title = "O maior.",
                    description = "Conheça o Fred. Atacante do maior time do Rio de Janeiro"
                ),


            )

        )
        val onboarginViewePager = findViewById<ViewPager2>(R.id.onboardingViwerPage)
        onboarginViewePager.adapter = onboardingItemsAdapter
        onboarginViewePager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            fun OnPageSelect(position: Int){
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onboarginViewePager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.imageNext).setOnClickListener{
            if(onboarginViewePager.currentItem+1<onboardingItemsAdapter.itemCount) {
                onboarginViewePager.currentItem += 1
            }else{
                navigatetoHome()
            }
        }
        findViewById<TextView>(R.id.textSkipe).setOnClickListener {
            navigatetoHome()
        }

        findViewById<MaterialButton>(R.id.buttonNext).setOnClickListener{
            navigatetoHome()

        }

        }

    private fun setupIndicators(){
        indicatorsContainer = findViewById(R.id.indicatorContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams : LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inative_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)

            }
        }

    }

    private fun navigatetoHome(){
        startActivity(Intent(applicationContext, HomeActivity::class.java))
        finish()
    }

    private fun setCurrentIndicator(position: Int){
        val childCount = indicatorsContainer.childCount
        for(i in 0 until childCount){

            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if(i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background

                    )

                )
            }else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )

                )

            }
        }

    }

}