package com.vendor.frro.manager

import android.content.om.FabricatedOverlay
import android.content.om.OverlayManager
import android.content.om.OverlayManagerTransaction
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button)?.setOnClickListener {
            val packageService = getSystemService<OverlayManager>()!!

            val fabricatedOverlay = FabricatedOverlay(
                "abcd",
                "com.vendor.drink.default"
            ).apply {
                setOwningPackage(packageName)

                targetOverlayable = "ThemeResources"
                setResourceValue(
                    "string/hello_world",
                    TypedValue.TYPE_STRING,
                    "Hello World from FRRO",
                    null
                )
            }

            val transaction = OverlayManagerTransaction
                .Builder()
                .registerFabricatedOverlay(fabricatedOverlay)
                .setEnabled(fabricatedOverlay.identifier, true)
                .build()

            packageService.commit(transaction)
        }
    }
}