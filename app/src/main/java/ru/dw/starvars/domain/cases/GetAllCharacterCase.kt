package ru.dw.starvars.domain.cases



import androidx.lifecycle.LiveData
import ru.dw.starvars.data.repositories.list.RepositoryIpl
import ru.dw.starvars.domain.model.CharacterItemView

class GetAllCharacterCase(private val repository: RepositoryIpl) {

    operator fun invoke(): LiveData<List<CharacterItemView>> {
       return repository.getAllCharacters()
    }
}