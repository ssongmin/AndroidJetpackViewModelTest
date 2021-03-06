package com.example.mvvptest1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType {
    PLUS, MINUS
}

//데이터의 변경
class MyNumberViewModel :ViewModel(){

    companion object {
        const val TAG = "MyNumberViewModel"
    }
    //뮤터블 라이브 데이터 - 수정 가능
    //라이브 데이터 - 읽기 전용

    //내부에서 설정하는 자료형은 뮤터블
    //변수명 앞에 _는 이 클레스 안에서만 사용가능한거(꺼낼때는 빼고 읽기전용으로)
    private val _currentValue = MutableLiveData<Int>()

    val currentValue: LiveData<Int>
        get() = _currentValue

    // 초기값 설정
    init {
        Log.d(TAG, "생성자 호출")
        _currentValue.value = 0
    }

    fun updateValue(actionType: ActionType, input: Int){
        when(actionType){
            ActionType.PLUS ->
                _currentValue.value = _currentValue.value?.plus(input)

            ActionType.MINUS ->
                _currentValue.value = _currentValue.value?.minus(input)
        }
    }
}