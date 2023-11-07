package com.example.elabsofflineattendanceapp

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import java.util.*



class QR_Scanner : AppCompatActivity() {
    val CAMERA_PERMISSION=1111
    private lateinit var scanner: CodeScanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scanner)
        val scannerview:CodeScannerView=findViewById(R.id.scannerView)
        scanner= CodeScanner(this,scannerview)
        scanner.camera=CodeScanner.CAMERA_BACK
        scanner.autoFocusMode=AutoFocusMode.SAFE
        scanner.formats=CodeScanner.ALL_FORMATS
        scanner.isAutoFocusEnabled=true
        scanner.isFlashEnabled=false
        scanner.scanMode=ScanMode.SINGLE
        scanner.decodeCallback= DecodeCallback {
            runOnUiThread{
                //mark attendance
                Toast.makeText(this,"${it.text}",Toast.LENGTH_LONG).show()
            }
        }
        scanner.errorCallback= ErrorCallback {
            runOnUiThread{
                Toast.makeText(this,"Error: ${it.message}",Toast.LENGTH_LONG).show()
            }
        }
        scanner.startPreview()
        checkpermission()



    }
    fun checkpermission(){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),CAMERA_PERMISSION)
        }else{
                scanner.startPreview()
            }
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==CAMERA_PERMISSION&&grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            scanner.startPreview()
        }else{
            Toast.makeText(this,"Give Camera Permission",Toast.LENGTH_LONG).show()
        }
    }


    override fun onResume() {
        super.onResume()
        scanner.startPreview()
    }

    override fun onPause() {
        scanner.releaseResources()
        super.onPause()

    }
}