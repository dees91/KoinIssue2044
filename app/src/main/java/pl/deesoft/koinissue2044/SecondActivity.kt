package pl.deesoft.koinissue2044

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.scope.AndroidScopeComponent
import org.koin.core.scope.Scope
import pl.deesoft.koinissue2044.di.retainedScope

class SecondActivity : AppCompatActivity(), AndroidScopeComponent, ContainerScope {

    override val scope: Scope by retainedScope(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Add third fragment to demonstrate shared use case across activities
        supportFragmentManager.beginTransaction()
            .replace(R.id.second_activity_container, ThirdFragment())
            .commit()
    }
}
