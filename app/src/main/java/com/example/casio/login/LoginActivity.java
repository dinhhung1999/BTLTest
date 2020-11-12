package com.example.casio.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import pub.devrel.easypermissions.EasyPermissions;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.casio.MainActivity;
import com.example.casio.R;
import com.example.casio.Utils.SQLHelper;
import com.example.casio.Utils.VFMSharePreference;
import com.example.casio.databinding.ActivityLoginBinding;
import com.example.casio.models.User;
import com.google.android.material.textfield.TextInputLayout;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    User user;
    final String MD5 = "MD5";
    final int LOGINACTIVITY = 1999;
    ActivityLoginBinding binding;
    LoginViewListener listener = new LoginViewListener();
    SQLHelper sqlHelper;
    Valilator valilator;
    List<User> users = new ArrayList<>();
    VFMSharePreference sharePreference;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        if (!checkRequiredPermissions()) checkRequiredPermissions();
        valilator = new Valilator();
        sqlHelper = new SQLHelper(getBaseContext());
        binding.btnSignIn.setOnClickListener(v -> {
            binding.user.setError(null);
            binding.user.setErrorEnabled(false);
            binding.pass.setError(null);
            binding.pass.setErrorEnabled(false);
            listener.onLogin(binding.user,binding.pass);
        });
        binding.btnSignUp.setOnClickListener(v -> {
            binding.user.setError(null);
            binding.user.setErrorEnabled(false);
            binding.pass.setError(null);
            binding.pass.setErrorEnabled(false);
            listener.onRegister(binding.user,binding.pass);
        });
    }
    public class LoginViewListener {
        void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
            if (sqlHelper.getAllUser().size()!=0) users = sqlHelper.getAllUser();
            else {
                loginUser.setError(getText(R.string.usernameNotExist));
//                Toast.makeText(getBaseContext(),getText(R.string.login_failed),Toast.LENGTH_SHORT).show();
                return;
            }
            if (valilator.isValidEmail(loginUser.getEditText().getText().toString())){
                if (!loginUser.getEditText().getText().toString().isEmpty()){
                    if (users.size()!=0) {
                        user = sqlHelper.getUser(loginUser.getEditText().getText().toString());
                        if (user!=null) {
                            if (md5(loginPass.getEditText().getText().toString()).equals(user.getPassword())) {
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                Toast.makeText(getBaseContext(),getText(R.string.loginSuccess)+" \n"+ getText(R.string.wellcome)+" "+user.getUsername(),Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }
                            else {
                                loginPass.setError(getText(R.string.incorrectPassword));
                                return;
                            }
                        } else {
                            loginUser.setError(getText(R.string.usernameNotExist));
                            return;
                        }
                    } else {
                        loginUser.setError(getText(R.string.usernameNotExist));
//                        Toast.makeText(getBaseContext(),getText(R.string.login_failed),Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    loginUser.setError(getText(R.string.username_empty));
//                    Toast.makeText(getBaseContext(),getText(R.string.login_failed),Toast.LENGTH_SHORT).show();
                    return;
                }
            } else if(valilator.validatePhoneNumber(loginUser.getEditText().getText().toString())){
                    if (users.size()!=0) {
                        user = sqlHelper.getUser(loginUser.getEditText().getText().toString());
                        if (user!=null) {
                            if (md5(loginPass.getEditText().getText().toString()).equals(user.getPassword())) {
                                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                    Toast.makeText(getBaseContext(),getText(R.string.loginSuccess)+" \n"+ getText(R.string.wellcome)+" "+user.getUsername(),Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                            else {
                                loginPass.setError(getText(R.string.incorrectPassword));
                                return;
                            }
                        } else {
                            loginUser.setError(getText(R.string.usernameNotExist));
                            return;
                        }
//
                    } else {
                        loginUser.setError(getText(R.string.usernameNotExist));
//                        Toast.makeText(getBaseContext(),getText(R.string.login_failed),Toast.LENGTH_SHORT).show();
                        return;
                    }
            }
            else {
                loginUser.setError(getText(R.string.invalid_email));
//                Toast.makeText(getBaseContext(),getText(R.string.login_failed),Toast.LENGTH_SHORT).show();
                return;
            }
        }
    public void onRegister(TextInputLayout loginUser, TextInputLayout loginPass) {
            if (sqlHelper.getAllUser().size()!=0) users = sqlHelper.getAllUser();
            if (loginUser.getEditText().getText().toString().isEmpty()) {
                loginUser.setError(getText(R.string.username_empty));
                return;
            }
            if (!valilator.isValidEmail(loginUser.getEditText().getText().toString()) && !valilator.validatePhoneNumber(loginUser.getEditText().getText().toString()) ) {
                loginUser.setError(getText(R.string.invalid_email));
                return;
            }
            if (users.size()!=0) {
                for (int i =0; i<users.size();i++) {
                    if (users.get(i).getUsername().equals(loginUser.getEditText().getText().toString())) {
                        loginUser.setError(getText(R.string.usernameAvailable));
                        return;
                    }
                }
            }
            if (!valilator.hasLength(loginPass.getEditText().getText().toString())) {
                loginPass.setError(getText(R.string.invalid_password));
                return;
            }
            if (!valilator.hasLowerCase(loginPass.getEditText().getText().toString())) {
                loginPass.setError(getText(R.string.hasLower));
                return;
            }if (!valilator.hasUpperCase(loginPass.getEditText().getText().toString())) {
                loginPass.setError(getText(R.string.hasUpper));
                return;
            }if (!valilator.hasSymbol(loginPass.getEditText().getText().toString())) {
                loginPass.setError(getText(R.string.hasSymbol));
                return;
            }if (valilator.hasSpace(loginPass.getEditText().getText().toString())) {
                loginPass.setError(getText(R.string.hasSpaces));
                return;
            } sqlHelper.insertUser(loginUser.getEditText().getText().toString().trim(),md5(loginPass.getEditText().getText().toString()));
            Toast.makeText(getBaseContext(),getText(R.string.registerSuccess),Toast.LENGTH_SHORT).show();
        }
    }
    public String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private boolean checkRequiredPermissions(){
        String[] perms ={Manifest.permission.CHANGE_CONFIGURATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this,perms)){
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this,getString(R.string.message_request_permission_read_phone_state),LOGINACTIVITY,perms);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    public void login(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        }, 1000);
    }
    public void onRegister(TextInputLayout loginUser, TextInputLayout loginPass) {
        if (sqlHelper.getAllUser().size()!=0) users = sqlHelper.getAllUser();
        if (loginUser.getEditText().getText().toString().isEmpty()) {
            loginUser.setError(getText(R.string.username_empty));
            return;
        }
        if (!valilator.isValidEmail(loginUser.getEditText().getText().toString()) && !valilator.validatePhoneNumber(loginUser.getEditText().getText().toString()) ) {
            loginUser.setError(getText(R.string.invalid_email));
            return;
        }
        if (users.size()!=0) {
            for (int i =0; i<users.size();i++) {
                if (users.get(i).getUsername().equals(loginUser.getEditText().getText().toString())) {
                    loginUser.setError(getText(R.string.usernameAvailable));
                    return;
                }
            }
        }
        if (!valilator.hasLength(loginPass.getEditText().getText().toString())) {
            loginPass.setError(getText(R.string.invalid_password));
            return;
        }
        if (!valilator.hasLowerCase(loginPass.getEditText().getText().toString())) {
            loginPass.setError(getText(R.string.hasLower));
            return;
        }if (!valilator.hasUpperCase(loginPass.getEditText().getText().toString())) {
            loginPass.setError(getText(R.string.hasUpper));
            return;
        }if (!valilator.hasSymbol(loginPass.getEditText().getText().toString())) {
            loginPass.setError(getText(R.string.hasSymbol));
            return;
        }if (valilator.hasSpace(loginPass.getEditText().getText().toString())) {
            loginPass.setError(getText(R.string.hasSpaces));
            return;
        } sqlHelper.insertUser(loginUser.getEditText().getText().toString().trim(),md5(loginPass.getEditText().getText().toString()));
        Toast.makeText(getBaseContext(),getText(R.string.registerSuccess),Toast.LENGTH_SHORT).show();
    }

}