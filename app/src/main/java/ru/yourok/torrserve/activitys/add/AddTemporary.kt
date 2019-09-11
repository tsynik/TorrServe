package ru.yourok.torrserve.activitys.add

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.yourok.torrserve.activitys.play.PlayActivity

class AddTemporary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var torrentLink = ""
        var title = ""

        if (intent.action != null && intent.action.equals(Intent.ACTION_VIEW)) {
            intent.data?.let {
                torrentLink = it.toString()
            }
        }

        ///Intent send
        if (intent.action != null && intent.action.equals(Intent.ACTION_SEND)) {
            if (intent.getStringExtra(Intent.EXTRA_TEXT) != null)
                torrentLink = intent.getStringExtra(Intent.EXTRA_TEXT)
            if (intent.extras.get(Intent.EXTRA_STREAM) != null)
                torrentLink = intent.extras.get(Intent.EXTRA_STREAM).toString()
        }

        if (torrentLink.isEmpty()) {
            finish()
            return
        }

        if (intent.hasExtra("Title"))
            title = intent.getStringExtra("Title")

        if (intent.hasExtra("title"))
            title = intent.getStringExtra("title")

        val vintent = Intent(this, PlayActivity::class.java)
        vintent.setData(Uri.parse(torrentLink))
        vintent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        vintent.action = Intent.ACTION_VIEW
        vintent.putExtra("DontSave", true)
        vintent.putExtra("Title", title)
        startActivity(vintent)

        finish()
    }
}