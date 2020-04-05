package cricport.com.cricport;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by BADE on 18-02-2016.
 */
public class EditMyProfileFragment extends Fragment {
    private ImageButton backBtn;

    public EditMyProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewToReturn = inflater.inflate(R.layout.fragment_edit_profile, container, false);
//        backBtn = (ImageButton) viewToReturn.findViewById(R.id.fragment_edit_profile_ibtn_back);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Profile");
//        backBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager FM = getFragmentManager();
//                FragmentTransaction FT = FM.beginTransaction();
//                MyDiscoveryFragment F2 = new MyDiscoveryFragment();
//
//                FT.replace(R.id.container_body, F2);
//                FT.commit();
//            }
//        });
        // Inflate the layout for this fragment


        return viewToReturn;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
