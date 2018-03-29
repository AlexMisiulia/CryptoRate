package best.lang.cryptorates.utils

import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.fragmentTransaction(codeBlock: FragmentTransaction.() -> FragmentTransaction) {
    supportFragmentManager.beginTransaction()
            .codeBlock()
            .commit()
}