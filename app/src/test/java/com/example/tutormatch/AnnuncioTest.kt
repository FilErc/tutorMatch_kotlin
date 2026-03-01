package com.example.tutormatch.ui.viewmodel

import com.example.tutormatch.data.model.Annuncio
import com.google.firebase.firestore.GeoPoint
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.junit.runner.RunWith
import androidx.lifecycle.Observer

@RunWith(RobolectricTestRunner::class)
class AnnuncioTest {

    private lateinit var application: android.app.Application
    private lateinit var viewModel: AnnunciViewModel

    @Before
    fun setUp() {
        application = RuntimeEnvironment.application
        viewModel = AnnunciViewModel(application)
    }

    @Test
    fun `test creazione oggetto Annuncio con parametri`() {
        val geoPoint = GeoPoint(45.0, 12.0)
        val annuncio = Annuncio(
            id = "1",
            descrizione = "Ripetizioni di matematica",
            materia = "Matematica",
            mod_on = true,
            mod_pres = false,
            posizione = geoPoint,
            prezzo = "20",
            tutor = null
        )

        // Aggiungere l'annuncio alla lista esistente
        val annunciList = mutableListOf<Annuncio>()
        annunciList.add(annuncio)
        viewModel._listaAnnunci.postValue(annunciList)

        // Observer per verificare il risultato
        val observer = Observer<List<Annuncio>> { lista ->
            assertEquals(1, lista.size)
            assertEquals("Ripetizioni di matematica", lista[0].descrizione)
        }
        viewModel.listaAnnunci.observeForever(observer)

        // Cleanup
        viewModel.listaAnnunci.removeObserver(observer)
    }
}

//    @Test
//    fun `test funzione getModalita`() {
//        val annuncioOnline = Annuncio(mod_on = true, mod_pres = false)
//        assertEquals("Modalità: Online", annuncioOnline.getModalita())
//
//        val annuncioPresenza = Annuncio(mod_on = false, mod_pres = true)
//        assertEquals("Modalità: Presenza", annuncioPresenza.getModalita())
//
//        val annuncioEntrambi = Annuncio(mod_on = true, mod_pres = true)
//        assertEquals("Modalità: Online e Presenza", annuncioEntrambi.getModalita())
//
//        val annuncioNessuna = Annuncio(mod_on = false, mod_pres = false)
//        assertEquals("Modalità: Non specificata", annuncioNessuna.getModalita())
//    }
