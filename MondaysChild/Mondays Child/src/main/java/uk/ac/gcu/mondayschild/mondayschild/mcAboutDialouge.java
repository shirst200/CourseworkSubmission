package uk.ac.gcu.mondayschild.mondayschild;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by Administrator on 08/12/2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class mcAboutDialouge extends DialogFragment
{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        //This displays a message that sits on the current screen until dismissed
        AlertDialog.Builder mcAboutDialog = new AlertDialog.Builder(getActivity());
        //This is the message
        mcAboutDialog.setMessage("This app will take your birthday and tell you what celebrities share your birthday and what star sign you share with them").setPositiveButton("Ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        mcAboutDialog.setTitle("about");
        mcAboutDialog.setIcon(R.drawable.ic_menu_action_about);

        return mcAboutDialog.create();
    }
}
