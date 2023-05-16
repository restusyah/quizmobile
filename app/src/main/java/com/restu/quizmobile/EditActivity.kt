package com.restu.quizmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.restu.quizmobile.databinding.ActivityEditBinding
import com.restu.quizmobile.room.Contant
import com.restu.quizmobile.room.NoteDB
import com.restu.quizmobile.room.Skripsi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class EditActivity : AppCompatActivity() {

    val db by lazy { NoteDB(this) }
    private var noteId: Int = 0

    private lateinit var binding : ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupListener()
    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when(intentType){
            Contant.TYPE_CREATE -> {
                binding.buttonUpdate.visibility = View.GONE
            }
            Contant.TYPE_READ -> {
                binding.buttonSave.visibility = View.GONE
                binding.buttonUpdate.visibility = View.GONE
                getNote()
            }
            Contant.TYPE_UPDATE -> {
                binding.buttonSave.visibility = View.GONE
                getNote()
            }
        }
    }

    private fun setupListener() {
        binding.buttonSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().addNote(
                    Skripsi(0, binding.editNISN.text.toString(), binding.editNAMA.text.toString(), binding.editALAMAT.text.toString(),binding.editASAL.text.toString())
                )
                finish()
            }
        }

        binding.buttonUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().updateNote(
                    Skripsi(noteId, binding.editNISN.text.toString(), binding.editNAMA.text.toString(), binding.editALAMAT.text.toString(),binding.editASAL.text.toString())
                )
                finish()
            }
        }
    }

    fun getNote(){
        noteId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNote(noteId)[0]
            binding.editNISN.setText(notes.nisn)
            binding.editNAMA.setText(notes.nama)
            binding.editALAMAT.setText(notes.alamat)
            binding.editASAL.setText(notes.asalsekolah)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}