package com.example.criminalintent.ui.crimeEdit

import android.app.Activity
import android.content.Context
import android.hardware.input.InputManager
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.criminalintent.R
import com.example.criminalintent.core.Utils
import com.example.criminalintent.core.Utils.Companion.getFormattedStringForChooseDateButton
import com.example.criminalintent.database.entities.Crime
import com.example.criminalintent.databinding.FragmentCrimeEditBinding
import com.example.criminalintent.ui.crimeList.recyclerView.CrimeListAdapter
import com.example.criminalintent.ui.dialogs.DatePickerFragment
import java.util.*

class CrimeEditFragment: Fragment(), DatePickerFragment.CallBacks {

    private lateinit var binding: FragmentCrimeEditBinding

    private lateinit var viewModel: CrimeEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_crime_edit, container, false)

        val appContext = this.requireContext().applicationContext

        val args = CrimeEditFragmentArgs.fromBundle(requireArguments())


        val factory = CrimeEditViewModelFactory(appContext, null)

        viewModel = ViewModelProvider(this, factory).get(CrimeEditViewModel::class.java)

        val crime = when(args.crimePos) {
            -1 -> null
            else -> viewModel.crimes.value?.get(args.crimePos)
        }

        if (crime != null) {
            viewModel.setCrime(crime)
            setStartValues(crime)
        }
        else {
            setDefaultValues()
        }

        return binding.root
    }


    private fun setDefaultValues() {
        binding.chooseDateButton.text = Utils.getFormattedStringForChooseDateButton(Date())
    }

    private fun setStartValues(crime: Crime) {

        viewModel.setSolved(crime.isDisclosed)
        viewModel.setTitle(crime.title)
        viewModel.setDescription(crime.description)
        viewModel.setDate(crime.date)

        binding.descriptionEditText.setText(crime.description)
        binding.titleEditText.setText(crime.title)
        binding.isSolvedCheckBox.isChecked = crime.isDisclosed
        binding.chooseDateButton.text = Utils.getFormattedStringForChooseDateButton(crime.date)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllClickListeners()

    }

    private fun setAllClickListeners() {

        binding.applyButton.setOnClickListener {
            applyChanges()

            hideKeyboard(requireContext())

            val destination = R.id.action_crimeEditFragment_to_crimeListFragment
            findNavController().navigate(destination)
        }

        binding.chooseDateButton.setOnClickListener {
            DatePickerFragment.newInstance(viewModel.getCurrentDate()).apply {
                setTargetFragment(this@CrimeEditFragment, REQUEST_DATE_CODE)
                show(this@CrimeEditFragment.parentFragmentManager, DIALOG_DATE)
            }
        }

    }

    private fun hideKeyboard(context: Context) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.descriptionEditText.windowToken, 0)
        imm.hideSoftInputFromWindow(binding.titleEditText.windowToken, 0)
    }

    private fun applyChanges() {

        setAllData()

        viewModel.applyChanges()
    }

    private fun setAllData() {

        viewModel.setDescription(binding.descriptionEditText.text.toString())
        viewModel.setTitle(binding.titleEditText.text.toString())
        viewModel.setSolved(binding.isSolvedCheckBox.isChecked)

    }


    override fun onDateSelected(date: Date) {
        viewModel.setDate(date)

        binding.chooseDateButton.text = Utils.getFormattedStringForChooseDateButton(date)
    }

    companion object {
        private const val DIALOG_DATE = "DialogDate"
        private const val REQUEST_DATE_CODE = 0
    }

}