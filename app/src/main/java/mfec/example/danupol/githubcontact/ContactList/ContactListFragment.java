package mfec.example.danupol.githubcontact.ContactList;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import mfec.example.danupol.githubcontact.R;
import mfec.example.danupol.githubcontact.User;

public class ContactListFragment extends Fragment implements AdapterView.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private ContactListViewModel mViewModel;
    private ListView contactList;
    private OnFragmentInteractionListener listener;
    private SwipeRefreshLayout refreshLayout;

    public static ContactListFragment newInstance(OnFragmentInteractionListener listener) {
        ContactListFragment fragment = new ContactListFragment();
        fragment.registerListener(listener);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_list_fragment, container, false);
        contactList = view.findViewById(R.id.listview_contact);
        contactList.setOnItemClickListener(ContactListFragment.this);
        refreshLayout = view.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(ContactListFragment.this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(ContactListViewModel.class);
        // TODO: Use the ViewModel
        observeData();
        onRefresh();
    }

    public void observeData(){
        mViewModel.getDisplayUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                if(users != null) {
                    ContactListViewAdapter adapter =
                            new ContactListViewAdapter(requireContext(), R.layout.contact_item, users);
                    contactList.setAdapter(adapter);
                    refreshLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        User selectedUser = (User) adapterView.getItemAtPosition(position);
        listener.OnPressUser(selectedUser.getLogin());
    }

    public void registerListener(OnFragmentInteractionListener listener) {
        this.listener = listener;
    }

    @Override
    public void onRefresh() {
        mViewModel.getAllContact();
    }

    public interface OnFragmentInteractionListener {
        void OnPressUser(String userid);
    }
}
