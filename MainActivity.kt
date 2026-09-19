package com.example.otgrecoveryassistant

import android.app.Activity
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    private lateinit var status: TextView
    private lateinit var details: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 28, 28, 28)
        }

        root.addView(TextView(this).apply {
            text = "OTG Recovery Assistant"
            textSize = 26f
        })

        root.addView(TextView(this).apply {
            text = "\nSafe, authorized recovery & backup helper\n"
            textSize = 16f
        })

        status = TextView(this).apply {
            text = "Status: Not checked"
            textSize = 18f
        }
        root.addView(status)

        root.addView(Button(this).apply {
            text = "🔌 Check OTG Connection"
            setOnClickListener { checkUsb() }
        })

        root.addView(Button(this).apply {
            text = "☁️ Google Photos Recovery"
            setOnClickListener {
                details.text =
                    "1. Check Google Photos on another phone.\n" +
                    "2. Check every Google account already signed in.\n" +
                    "3. Do NOT factory-reset the old phone.\n" +
                    "4. If photos are only on internal storage, use a trusted technician."
            }
        })

        root.addView(TextView(this).apply {
            text = "\n⚠️ IMPORTANT\n" +
                    "This app never reads, guesses, cracks, or bypasses a phone PIN. " +
                    "It only checks USB connectivity and provides safe recovery guidance."
            textSize = 15f
        })

        details = TextView(this).apply {
            text = "\nConnect your OTG adapter and the other phone, then tap the button above."
            textSize = 15f
        }
        root.addView(details)

        setContentView(root)
    }

    private fun checkUsb() {
        val manager = getSystemService(USB_SERVICE) as UsbManager
        val devices: HashMap<String, UsbDevice> = manager.deviceList

        if (devices.isEmpty()) {
            status.text = "Status: No USB device detected"
            details.text =
                "Check the OTG adapter, cable, and USB connection.\n" +
                "A locked Android phone may not expose its storage."
            return
        }

        status.text = "Status: USB device detected"
        details.text = devices.values.joinToString("\n\n") {
            "Device: ${it.deviceName}\nVID: ${it.vendorId}  PID: ${it.productId}"
        } + "\n\nIf storage is not accessible, do not reset the phone."
    }
}
