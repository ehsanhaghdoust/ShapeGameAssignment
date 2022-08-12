package ehsan.haghdoust.shapegameassignment.viewModel.baseViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class BaseViewModel: ViewModel() {

    private val supervisorJob = SupervisorJob()

    val coroutineIO = CoroutineScope(Dispatchers.IO)

    val coroutineMain = CoroutineScope(Dispatchers.Main)
}