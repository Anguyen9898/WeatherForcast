package com.anguyen.weatherforecast_demo.commons

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.animation.addListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

inline fun View.onClick(crossinline onClickHandler: () -> Unit){
    setOnClickListener { onClickHandler() }
}

inline fun View.onTouch(crossinline onTouchHandler: (MotionEvent) -> Unit){
    setOnTouchListener { _, event ->
        onTouchHandler(event)
        performClick()
        return@setOnTouchListener true
    }
}

fun RecyclerView.setupVertically(from: Context, adapterData: RecyclerView.Adapter<*>){
    apply {
        adapter = adapterData
        layoutManager = LinearLayoutManager(from, RecyclerView.VERTICAL, false)
    }
}

fun RecyclerView.setupHorizontally(from: Context, adapterData: RecyclerView.Adapter<*>){
    apply {
        adapter = adapterData
        layoutManager = LinearLayoutManager(from, RecyclerView.HORIZONTAL, false)
    }
}

fun View.bgColorChangeAnimation(to: Int){
    val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), this.solidColor, to)

    colorAnimation.apply {
        duration = 250 //milliseconds

        addUpdateListener {
            setBackgroundColor(it.animatedValue as Int)
        }

        start()
    }

}

inline fun EditText.onTextChanged(crossinline onTextChangeHandler: (String) -> Unit){
    addTextChangedListener(object : TextWatcher{
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChangeHandler(s?.toString() ?: "")
        }
    })
}

inline fun SearchView.onQueryRequest(crossinline onQueryTextChange: (String?) -> Unit
                                     , crossinline onQueryTextSubmit: (String?) -> Unit){

    setOnQueryTextListener( object : SearchView.OnQueryTextListener{

        override fun onQueryTextSubmit(query: String?): Boolean {
            onQueryTextSubmit(query)
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            onQueryTextChange(newText)
            return false
        }

    })

}

inline fun EditText.onFocusChanged(crossinline onFocusChangeHandler: (modeId: Int) -> Unit){
    val resizeMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
    val panMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN

    setOnFocusChangeListener{_, hasFocus ->
        if(hasFocus) {
            onFocusChangeHandler(resizeMode)
        } else {
            onFocusChangeHandler(panMode)
        }
    }
}

inline fun BottomNavigationView.onItemSelected(crossinline onItemSelectedHandler
                                               : (menuItem: MenuItem) -> Unit){
    setOnNavigationItemSelectedListener{
        onItemSelectedHandler(it)
        return@setOnNavigationItemSelectedListener true
    }
}

inline fun RadioGroup.onCheckedChangeListener(crossinline onItemClickHandler: () -> Unit){
    setOnCheckedChangeListener{ _, _ ->  onItemClickHandler()}
}

fun Fragment.setup(from: FragmentActivity, id: Int, bundle: Bundle) {
    this.arguments = bundle
    from.supportFragmentManager
        .beginTransaction()
        .replace(id, this)
        .commit()
}

fun AppCompatActivity.backToPrevious() {
    if(supportFragmentManager.backStackEntryCount > 0){
        supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}

inline fun Toolbar.onItemClick(crossinline onItemClickHandler: (menuItem: MenuItem) -> Unit){
    setOnMenuItemClickListener {
        onItemClickHandler (it)
        true
    }
}
