package ru.dw.starvars.domain.usecases



import androidx.lifecycle.LiveData
import ru.dw.starvars.data.repositories.list.RepositoryListIpl
import ru.dw.starvars.domain.model.CharacterItemView
import javax.inject.Inject

class GetAllCharacterCase @Inject constructor(private val repository: RepositoryListIpl) {

    operator fun invoke(): LiveData<List<CharacterItemView>> {
       return repository.getAllCharacters()
    }
}