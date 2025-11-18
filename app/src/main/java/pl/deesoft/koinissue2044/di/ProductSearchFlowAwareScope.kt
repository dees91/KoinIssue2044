package pl.deesoft.koinissue2044.di

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.AndroidScopeComponent
import org.koin.android.scope.getScopeOrNull
import org.koin.androidx.scope.ScopeHandlerViewModel
import org.koin.core.qualifier.TypeQualifier
import org.koin.core.scope.Scope
import org.koin.ext.getFullName
import pl.deesoft.koinissue2044.ContainerScope

fun ContainerScope.retainedScope(activity: ComponentActivity) = lazy { createActivityRetainedScope(activity) }

private fun ContainerScope.createActivityRetainedScope(activity: ComponentActivity): Scope {
    val scopeViewModel = activity.viewModels<ScopeHandlerViewModel>().value
    if (scopeViewModel.scope == null) {
        val scope = activity.getKoin().createScope(
            ContainerScope::class.getFullName() + "@" + this.hashCode(),
            TypeQualifier(ContainerScope::class)
        )
        scopeViewModel.scope = scope
    }
    return scopeViewModel.scope!!
}

fun Fragment.linkToParentActivityScope(fragmentScope: Scope): Scope {
    val parentActivity = activity ?: return fragmentScope

    val parentActivityScope = when (parentActivity) {
        is AndroidScopeComponent -> parentActivity.scope
        else -> parentActivity.getScopeOrNull()
    }

    if (parentActivityScope != null) {
        fragmentScope.unlink(parentActivityScope)
        fragmentScope.linkTo(parentActivityScope)
    } else {
        Log.e("linkToParentActivityScope", "Could not link fragment scope to parent activity scope")
    }

    return fragmentScope
}