package com.saulgargar.epicrew.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.saulgargar.androidext.liveDataObserve
import com.saulgargar.androidext.roundTwoDecimals
import com.saulgargar.domain.State

import com.saulgargar.epicrew.R
import com.saulgargar.epicrew.presentation.adapter.professions.ProfessionsRecyclerAdapter
import com.saulgargar.epicrew.presentation.adapter.friends.FriendsRecyclerAdapter
import com.saulgargar.epicrew.presentation.viewmodel.GnomeProfileViewModel
import com.saulgargar.gnomedata.domain.model.Gnome
import com.saulgargar.gnomedata.domain.model.GnomeUser
import kotlinx.android.synthetic.main.fragment_gnome_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GnomeProfileFragment : BaseFragment() {

    private val gnomeProfileViewModel: GnomeProfileViewModel by viewModel()
    private val args: GnomeProfileFragmentArgs by navArgs()

    private lateinit var professionsAdapter: ProfessionsRecyclerAdapter
    private lateinit var friendsAdapter: FriendsRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gnome_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getActivityContext().loader.show()
        initArgs()
        initObservers()
        initAdapters()
        initListeners()
        initRecyclerViews()
        gnomeProfileViewModel.recoverGnomeById()
    }

    private fun initArgs(){
        gnomeProfileViewModel.gnome.id = args.gnomeId
    }

    private fun initObservers(){
        liveDataObserve(gnomeProfileViewModel.stateRecoverGnome, ::onRecoverGnomesStateChange)
    }

    private fun onRecoverGnomesStateChange(state: State?) {
        state?.let { noNullState ->
            when (noNullState) {
                is State.Success -> {
                    val result = noNullState.responseTo<Gnome>()
                    setProfile(result)
                    getActivityContext().loader.dismiss()
                }
                is State.Failed -> {
                    getActivityContext().loader.dismiss()
                    getActivityContext().handleFailure(failure = noNullState.failure)
                }
            }
        }
    }

    private fun initAdapters() {
        friendsAdapter = FriendsRecyclerAdapter()
        professionsAdapter = ProfessionsRecyclerAdapter()
    }

    private fun initRecyclerViews(){
        friendsRv.run {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            this.adapter = friendsAdapter
        }
        professionsRv.run {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            this.adapter = professionsAdapter
        }
    }

    private fun initListeners(){
        backBt.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setProfile(gnome: Gnome){
        nameTv.text = gnome.name
        ageTv.text = gnome.age.toString()
        weightTv.text = gnome.weight.roundTwoDecimals()
        heightTv.text = gnome.height.roundTwoDecimals()
        hairColorTv.text = gnome.hairColor

        when(gnome.hairColor){
            GREEN_HAIR ->{
                hairColorTv.setTextColor(resources.getColor(R.color.green_hair))
            }
            RED_HAIR ->{
                hairColorTv.setTextColor(resources.getColor(R.color.red_hair))
            }
            BLACK_HAIR ->{
                hairColorTv.setTextColor(resources.getColor(R.color.black_hair))
            }
            GRAY_HAIR ->{
                hairColorTv.setTextColor(resources.getColor(R.color.gray_hair))
            }
            PINK_HAIR ->{
                hairColorTv.setTextColor(resources.getColor(R.color.pink_hair))
            }
            else ->{
                hairColorTv.setTextColor(resources.getColor(R.color.black_hair))
            }
        }

        showFriendList(gnome.friends)
        showProfessionList(gnome.professions)
    }

    private fun showFriendList(friends: List<String>){
        friendsAdapter.setFriendList(friends)
    }

    private fun showProfessionList(professions: List<String>){
        professionsAdapter.setProfessionList(professions)
    }

    companion object{
        private const val GREEN_HAIR = "Green"
        private const val RED_HAIR = "Red"
        private const val BLACK_HAIR = "Black"
        private const val GRAY_HAIR = "Gray"
        private const val PINK_HAIR = "Pink"
    }
}


