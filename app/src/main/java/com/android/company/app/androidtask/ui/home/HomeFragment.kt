package com.android.company.app.androidtask.ui.home

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.company.app.androidtask.R
import com.android.company.app.androidtask.common.getRSRPColor
import com.android.company.app.androidtask.common.getRSRQColor
import com.android.company.app.androidtask.common.getSINRColor
import com.android.company.app.androidtask.models.RandomValueResponse
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Runnable
import java.util.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val TAG = "HomeFragment"
    private val viewModel: HomeViewModel by viewModel()
    lateinit var handler: Handler
    lateinit var runnableCode: Runnable
    private var x = 1.0
    private lateinit var sINRFirstSeries: LineGraphSeries<DataPoint>
    private lateinit var sINRSecondSeries: LineGraphSeries<DataPoint>
    private lateinit var sINRThirdSeries: LineGraphSeries<DataPoint>
    private lateinit var rSRQFirstSeries: LineGraphSeries<DataPoint>
    private lateinit var rSRQSecondSeries: LineGraphSeries<DataPoint>
    private lateinit var rSRQThirdSeries: LineGraphSeries<DataPoint>
    private lateinit var rSRPFirstSeries: LineGraphSeries<DataPoint>
    private lateinit var rSRPSecondSeries: LineGraphSeries<DataPoint>
    private lateinit var rSRPThirdSeries: LineGraphSeries<DataPoint>

    var calendar: Calendar = Calendar.getInstance()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setInitialSINRSeries()
        setInitialRSRQSeries()
        setInitialRSRPSeries()
    }

    override fun onResume() {
        super.onResume()
        getPeriodicRandomValues()
    }
    private fun setInitialRSRQSeries() {
        rSRQFirstSeries = LineGraphSeries()
        rSRQFirstSeries.color = Color.GREEN
        rSRQFirstSeries.thickness = 8
        rSRQFirstSeries.dataPointsRadius = 16f
        rSRQFirstSeries.isDrawDataPoints =true

        rSRQSecondSeries = LineGraphSeries()
        rSRQSecondSeries.color = Color.GREEN
        rSRQSecondSeries.thickness = 8
        rSRQSecondSeries.dataPointsRadius = 16f
        rSRQSecondSeries.isDrawDataPoints =true

        rSRQThirdSeries = LineGraphSeries()
        rSRQThirdSeries.color = Color.GREEN
        rSRQThirdSeries.thickness = 8
        rSRQThirdSeries.dataPointsRadius = 16f
        rSRQThirdSeries.isDrawDataPoints =true
    }
    private fun setInitialRSRPSeries() {
        rSRPFirstSeries = LineGraphSeries()
        rSRPFirstSeries.color = Color.BLUE
        rSRPFirstSeries.thickness = 8
        rSRPFirstSeries.dataPointsRadius = 16f
        rSRPFirstSeries.isDrawDataPoints =true

        rSRPSecondSeries = LineGraphSeries()
        rSRPSecondSeries.color = Color.BLUE
        rSRPSecondSeries.thickness = 8
        rSRPSecondSeries.dataPointsRadius = 16f
        rSRPSecondSeries.isDrawDataPoints =true

        rSRPThirdSeries = LineGraphSeries()
        rSRPThirdSeries.color = Color.BLUE
        rSRPThirdSeries.thickness = 8
        rSRPThirdSeries.dataPointsRadius = 16f
        rSRPThirdSeries.isDrawDataPoints =true
    }

    private fun setInitialSINRSeries() {
        sINRFirstSeries = LineGraphSeries()
        sINRFirstSeries.color = Color.RED
        sINRFirstSeries.thickness = 8
        sINRFirstSeries.dataPointsRadius = 16f
        sINRFirstSeries.isDrawDataPoints =true

        sINRSecondSeries = LineGraphSeries()
        sINRSecondSeries.color = Color.RED
        sINRSecondSeries.thickness = 8
        sINRSecondSeries.dataPointsRadius = 16f
        sINRSecondSeries.isDrawDataPoints =true

        sINRThirdSeries = LineGraphSeries()
        sINRThirdSeries.color = Color.RED
        sINRThirdSeries.thickness = 8
        sINRThirdSeries.dataPointsRadius = 16f
        sINRThirdSeries.isDrawDataPoints =true
    }

    private fun getPeriodicRandomValues() {

        handler = Handler(Looper.getMainLooper())
        // Define the code block to be executed
        runnableCode = object : Runnable {
            override fun run() {
                // Do something here on the main thread
                getRandomValues()
                Log.d("Handlers", "Called on main thread")
                // Repeat this the same runnable code block again another 2 seconds
                // 'this' is referencing the Runnable object
                handler.postDelayed(this, 2_000)
            }
        }
        // Start the initial runnable task by posting through the handler
        handler.post(runnableCode)
    }


    private fun getRandomValues() {

        viewModel.getRandomValues().observe(viewLifecycleOwner, Observer {
            it?.let {
                updateUI(it)
            }
        })

    }

    private fun updateUI(values: RandomValueResponse) {
        rsrp_value_tv.text = "${values.rSRP}"
        rsrq_value_tv.text = "${values.rSRQ}"
        snr_value_tv.text = "${values.sINR}"

        snr_value_tv.setBackgroundColor(Color.parseColor(values.sINR?.getSINRColor()))
        rsrq_value_tv.setBackgroundColor(Color.parseColor(values.sINR?.getRSRQColor()))
        rsrp_value_tv.setBackgroundColor(Color.parseColor(values.rSRP?.getRSRPColor()))

        values.sINR?.let { drawSINRValuesOnGraph(it) }
        values.rSRQ?.let { drawRSRQValuesOnGraph(it) }
        values.rSRP?.let { drawRSRPValuesOnGraph(it) }
    }

    private fun drawRSRPValuesOnGraph(rSRP: Int) {
        when (rSRP) {
            in -60 downTo -140 -> {

                rSRPFirstSeries.appendData(DataPoint(x, rSRP.toDouble()), true, 90)
                firstGraph.addSeries(rSRPFirstSeries)
                x += .03
            }

            in 0 downTo -30 -> {

                rSRPSecondSeries.appendData(DataPoint(x, rSRP.toDouble()), true, 90)

                secondGraph.addSeries(rSRPSecondSeries)
                x += .03
            }

            in -10..30 -> {

                rSRPThirdSeries.appendData(DataPoint(x, rSRP.toDouble()), true, 90)
                thirdGraph.addSeries(rSRPThirdSeries)
                x += .03
            }
        }
    }

    private fun drawRSRQValuesOnGraph(rSRQ: Int) {

        when (rSRQ) {
            in -60 downTo -140 -> {

                rSRQFirstSeries.appendData(DataPoint(x, rSRQ.toDouble()), true, 90)
                firstGraph.addSeries(rSRQFirstSeries)
                x += .03
            }

            in 0 downTo -30 -> {

                rSRQSecondSeries.appendData(DataPoint(x, rSRQ.toDouble()), true, 90)

                secondGraph.addSeries(rSRQSecondSeries)
                x += .03
            }

            in -10..30 -> {

                rSRQThirdSeries.appendData(DataPoint(x, rSRQ.toDouble()), true, 90)
                thirdGraph.addSeries(rSRQThirdSeries)
                x += .03
            }
        }
    }

    private fun drawSINRValuesOnGraph(sINR: Int) {

        when (sINR) {
            in -60 downTo -140 -> {

                sINRFirstSeries.appendData(DataPoint(x, sINR.toDouble()), true, 90)
                firstGraph.addSeries(sINRFirstSeries)
                x += .03
            }

            in 0 downTo -30 -> {

                sINRSecondSeries.appendData(DataPoint(x, sINR.toDouble()), true, 90)

                secondGraph.addSeries(sINRSecondSeries)
                x += .03
            }

            in -10..30 -> {

                sINRThirdSeries.appendData(DataPoint(x, sINR.toDouble()), true, 90)
                thirdGraph.addSeries(sINRThirdSeries)
                x += .03
            }
        }
    }

//    private fun drawOnFirstGraph(values: RandomValueResponse) {
//
//        GlobalScope.launch {
//            withContext(Dispatchers.Main) {
//
//                sINRFirstSeries.appendData(DataPoint(x, values.sINR?.toDouble() ?: 0.0), true,90)
//                sINRFirstSeries.color = Color.RED
//                graph.addSeries(sINRFirstSeries)
//                x += .03
//            }
//        }
//
//
//    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnableCode)
    }
}