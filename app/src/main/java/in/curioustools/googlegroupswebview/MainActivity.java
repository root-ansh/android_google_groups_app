package in.curioustools.googlegroupswebview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final int CHROME_CUSTOM_TAB_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launchChromeCustomTab(this);

    }

    public void launchChromeCustomTab(Activity activity) {
        String url = "https://groups.google.com/";
        // Here is a method that returns the chrome package name

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent tabIntent = builder
                .setShowTitle(true)
                .setInstantAppsEnabled(true)
                .enableUrlBarHiding()
                .setStartAnimations(activity,android.R.anim.fade_in,android.R.anim.fade_out)
                .build();
        builder.setToolbarColor(activity.getApplicationContext().getResources().getColor(R.color.colorPrimary));

        tabIntent.intent.setPackage("com.android.chrome");
        tabIntent.intent.setData(Uri.parse(url));
        startActivityForResult(tabIntent.intent, CHROME_CUSTOM_TAB_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHROME_CUSTOM_TAB_REQUEST_CODE) {
            finish();
        }
    }

}
