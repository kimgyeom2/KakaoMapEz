package com.gy25m.mapfinal


import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.animation.Animation
import com.kakao.vectormap.label.Label
import com.kakao.vectormap.label.LabelLayer
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mapView = findViewById<MapView>(R.id.map_view)
        mapView.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {
                // 지도 API 가 정상적으로 종료될 때 호출됨
            }

            override fun onMapError(error: Exception) {
                // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
            }
        }, object : KakaoMapReadyCallback() {
            override fun onMapReady(kakaoMap: KakaoMap) {

                var styles = LabelStyles.from(
                    "myStyleId",
                    LabelStyle.from(R.drawable.green_marker).setZoomLevel(8),
                    LabelStyle.from(R.drawable.choonsik).setZoomLevel(11),
                    LabelStyle.from(R.drawable.green_marker)
                        .setTextStyles(32, Color.BLACK, 1, Color.GRAY).setZoomLevel(15)
                )

                styles = kakaoMap.labelManager!!.addLabelStyles(styles!!)

                val label = kakaoMap.labelManager!!.layer!!.addLabel(
                    LabelOptions.from(LatLng.from(37.476216,126.973209))
                        .setStyles(styles).setTexts("Gyeom")
                )

                kakaoMap.setOnLabelClickListener { kakaoMap, layer, label ->
                    Toast.makeText(this@MainActivity, label.texts[0], Toast.LENGTH_SHORT).show()
                }

            }




        })


    }


}