package com.saulgargar.epicrew.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.jem.rubberpicker.RubberRangePicker
import com.saulgargar.androidext.liveDataObserve
import com.saulgargar.domain.State

import com.saulgargar.epicrew.R
import com.saulgargar.epicrew.presentation.viewmodel.CreatureFinderViewModel
import com.saulgargar.gnomedata.domain.model.HairColor
import com.saulgargar.gnomedata.domain.model.Profession
import kotlinx.android.synthetic.main.fragment_creatures_finder.*
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreaturesFinderFragment : BaseFragment() {

    private val creatureFinderViewModel: CreatureFinderViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_creatures_finder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getActivityContext().loader.show()
        initRangeViews()
        initObservers()
        initListeners()
        creatureFinderViewModel.getHairColors()
    }

    private fun initListeners(){
        hairColorDd.setOnItemClickListener { _, _, position, _ ->
            getActivityContext().loader.show()
            creatureFinderViewModel.saveHairColor(position)
        }

        ageSlider.setOnRubberRangePickerChangeListener(object : RubberRangePicker.OnRubberRangePickerChangeListener{
            override fun onProgressChanged(rangePicker: RubberRangePicker, startValue: Int, endValue: Int, fromUser: Boolean) {
                creatureFinderViewModel.physicalFeaturesQuery.minAge = startValue
                creatureFinderViewModel.physicalFeaturesQuery.maxAge = endValue
                minAgeTv.text = startValue.toString()
                maxAgeTv.text = endValue.toString()
            }

            override fun onStartTrackingTouch(rangePicker: RubberRangePicker, isStartThumb: Boolean) {}

            override fun onStopTrackingTouch(rangePicker: RubberRangePicker, isStartThumb: Boolean) {}
        })

        heightSlider.setOnRubberRangePickerChangeListener(object : RubberRangePicker.OnRubberRangePickerChangeListener{
            override fun onProgressChanged(rangePicker: RubberRangePicker, startValue: Int, endValue: Int, fromUser: Boolean) {
                creatureFinderViewModel.physicalFeaturesQuery.minHeight = startValue
                creatureFinderViewModel.physicalFeaturesQuery.maxHeight = endValue
                minHeightTv.text = startValue.toString()
                maxHeightTv.text = endValue.toString()
            }

            override fun onStartTrackingTouch(rangePicker: RubberRangePicker, isStartThumb: Boolean) {}

            override fun onStopTrackingTouch(rangePicker: RubberRangePicker, isStartThumb: Boolean) {}
        })

        weightSlider.setOnRubberRangePickerChangeListener(object : RubberRangePicker.OnRubberRangePickerChangeListener{
            override fun onProgressChanged(rangePicker: RubberRangePicker, startValue: Int, endValue: Int, fromUser: Boolean) {
                creatureFinderViewModel.physicalFeaturesQuery.minWeight = startValue
                creatureFinderViewModel.physicalFeaturesQuery.maxWeight = endValue
                minWeightTv.text = startValue.toString()
                maxWeightTv.text = endValue.toString()
            }

            override fun onStartTrackingTouch(rangePicker: RubberRangePicker, isStartThumb: Boolean) {}

            override fun onStopTrackingTouch(rangePicker: RubberRangePicker, isStartThumb: Boolean) {}
        })

        searchBt.setOnClickListener {
            val action = CreaturesFinderFragmentDirections.actionCreauresFinderFragmentToCreatureFinderResultsFragment(creatureFinderViewModel.physicalFeaturesQuery)
            findNavController().navigate(action)
        }
    }

    private fun initObservers(){
        liveDataObserve(creatureFinderViewModel.stateGetHairColors, ::onRecoverProfessionsStateChange)
    }

    private fun onRecoverProfessionsStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Success -> {
                    val result = noNullState.responseTo<List<HairColor>>()
                    getActivityContext().loader.dismiss()
                    initProfessionDropDown(hairColors = result)
                }
                is State.Failed -> {
                    getActivityContext().loader.dismiss()
                    getActivityContext().handleFailure(failure = noNullState.failure)
                }
            }
        }
    }

    private fun initProfessionDropDown(hairColors: List<HairColor>){
        val professionAdapter = ArrayAdapter(context!!,R.layout.drop_down_menu_item,hairColors.map { it.color })
        hairColorDd.setAdapter(professionAdapter)
    }

    private fun initRangeViews(){
        ageSlider.setCurrentStartValue(creatureFinderViewModel.physicalFeaturesQuery.minAge)
        ageSlider.setCurrentEndValue(creatureFinderViewModel.physicalFeaturesQuery.maxAge)

        weightSlider.setCurrentStartValue(creatureFinderViewModel.physicalFeaturesQuery.minWeight)
        weightSlider.setCurrentEndValue(creatureFinderViewModel.physicalFeaturesQuery.maxWeight)

        heightSlider.setCurrentStartValue(creatureFinderViewModel.physicalFeaturesQuery.minHeight)
        heightSlider.setCurrentEndValue(creatureFinderViewModel.physicalFeaturesQuery.maxHeight)
    }

}
