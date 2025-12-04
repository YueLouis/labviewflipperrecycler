package vn.hcmute.labviewflipperrecycler;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyPagerAdapter extends FragmentStateAdapter {

    public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new NewOrderFragment();
            case 1:
                return new ConfirmFragment();
            case 2:
            default:
                return new ShippingFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // 3 tab
    }
}
