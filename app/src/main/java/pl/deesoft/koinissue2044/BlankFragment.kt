package pl.deesoft.koinissue2044

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import pl.deesoft.koinissue2044.di.linkToParentActivityScope
import pl.deesoft.koinissue2044.ui.MainViewModel
import pl.deesoft.koinissue2044.ui.theme.KoinIssue2044Theme

class BlankFragment : Fragment(), AndroidScopeComponent {

    private val _scope by fragmentScope(useParentActivityScope = false)

    override val scope: Scope get() = linkToParentActivityScope(_scope)

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                KoinIssue2044Theme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        WelcomeContent(
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WelcomeContent(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val greetingText by viewModel.greetingText.collectAsState()
    val sharedCallCount by viewModel.sharedCallCount.collectAsState()

    // Periodically refresh the shared call count to see updates from other fragments
    androidx.compose.runtime.LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(1000)
            viewModel.refreshCallCount()
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Fragment 1",
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = greetingText,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = "Shared UseCase Call Count: $sharedCallCount",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(8.dp)
        )

        Button(
            onClick = { viewModel.updateGreeting("First Fragment User") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Call UseCase")
        }
    }
}
