package com.example.srtravelexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CustomerTicketDetailsActivity extends AppCompatActivity {

    NavigationView nav_view;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    private FirebaseAuth authProfile;
    FirebaseUser firebaseUser;
    TextView navUsername, navUseremail;
    String fullname, email,phone,gender;
    String transactionid,busid,from,to,date,cost,seatamount,time;
    TextView busids,times,froms,tos,seatamounts,transactionids,seatss,costs,usernames,emails;
    DatabaseReference DBref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_ticket_details);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        busids=findViewById(R.id.busid);
        times=findViewById(R.id.time);
        froms=findViewById(R.id.from);
        tos=findViewById(R.id.to);
        seatamounts=findViewById(R.id.seatamount);
        transactionids=findViewById(R.id.transactionid);
        seatss=findViewById(R.id.seats);
        costs=findViewById(R.id.cost);
        //Helper help=new Helper();


        usernames=findViewById(R.id.username);
        emails=findViewById(R.id.email);
        String userID = firebaseUser.getUid();

        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("TicketDetails");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Tickets readUserDetails = snapshot.getValue(Tickets.class);
                if (readUserDetails != null) {
                    //busids,times,froms,tos,seatamounts,transactionids,seatss,costs,usernames,emails;

                    busids.setText(Add("Bus ID: ",readUserDetails.getBusid()));
                    times.setText(Add("Time: ",readUserDetails.getTime()));
                    froms.setText(Add("Journey From: ",readUserDetails.getFrom()));
                    tos.setText(Add("Journey to: ",readUserDetails.getTo()));
                    seatamounts.setText(Add("Total Seats: ",readUserDetails.getSeatamount()));
                    transactionids.setText(Add("Transaction Id: ",readUserDetails.getTransactionid()));
                    String temp="";
                    for(int i=0;i<readUserDetails.getSeats().size();i++)
                    {
                        temp=Add(temp," ",readUserDetails.getSeats().get(i));
                    }
                    seatss.setText(Add("Seats: ",temp));
                    costs.setText(Add("Costs: ",readUserDetails.getCost()));
                    usernames.setText(Add("Username: ",readUserDetails.getUsername()));
                    emails.setText(Add("Email: ",readUserDetails.getUsermail()));
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(CustomerTicketDetailsActivity.this, "Something went wrong! User's details are not available at this moment", Toast.LENGTH_SHORT).show();

            }
        });












        nav_view =(NavigationView)findViewById(R.id.navmenu);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setTitle("          Booking Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        updatenavHeader();


        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.menu_home:

                        Intent h = new Intent(CustomerTicketDetailsActivity.this, CustomerBookingActivity.class);
                        h.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(h);
                        overridePendingTransition(0, 0);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.menu_profile:
                        Intent p = new Intent(CustomerTicketDetailsActivity.this, CustomerProfileActivity.class);
                        p.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(p);
                        overridePendingTransition(0, 0);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.menu_Ticket_Information:
                        Intent t = new Intent(CustomerTicketDetailsActivity.this, CustomerTicketDetailsActivity.class);
                        t.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(t);
                        overridePendingTransition(0, 0);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.menu_Ticket_Cancel:
                        Intent ct = new Intent(CustomerTicketDetailsActivity.this, CustomerTicketCancelActivity.class);
                        ct.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(ct);
                        overridePendingTransition(0, 0);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.menu_help:
                        Intent he = new Intent(CustomerTicketDetailsActivity.this, CustomerHelpActivity.class);
                        he.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(he);
                        overridePendingTransition(0, 0);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.menu_customer_care:
                        Intent cc = new Intent(CustomerTicketDetailsActivity.this, CustomerCareActivity.class);
                        cc.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(cc);
                        overridePendingTransition(0, 0);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.menu_logout:
                        Intent lg = new Intent(CustomerTicketDetailsActivity.this, CustomerLoginActivity.class);
                        lg.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(lg);
                        overridePendingTransition(0, 0);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        authProfile.signOut();
                        break;
                }


                return true;
            }
        });
    }
    private void showUserProfile(FirebaseUser firebaseUser,String name) {
        String userID = firebaseUser.getUid();

        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("TicketDetails");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Tickets readUserDetails = snapshot.getValue(Tickets.class);
                if (readUserDetails.username.equals(name)) {
                    fullname = readUserDetails.username;
                    email=readUserDetails.usermail;

                    phone = readUserDetails.userphone;
                    //transactionid,busid,from,to,date,cost,seatamount,time;
                    transactionid=readUserDetails.transactionid;
                    busid=readUserDetails.busid;
                    from=readUserDetails.from;
                    to=readUserDetails.to;
                    date=readUserDetails.date;
                    cost=readUserDetails.cost;
                    seatamount=readUserDetails.seatamount;
                    time=readUserDetails.time;
                    //busids,times,froms,tos,seatamounts,transactionids,seatss,costs,usernames,emails

                    busids.setText(busid);
                    times.setText(time);
                    froms.setText(from);
                    tos.setText(to);
                    seatamounts.setText(seatamount);
                    transactionids.setText(transactionid);
                    costs.setText(cost);
                    usernames.setText(fullname);
                    emails.setText(email);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(CustomerTicketDetailsActivity.this, "Something went wrong! User's details are not available at this moment", Toast.LENGTH_SHORT).show();

            }
        });
    }



    public void updatenavHeader() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navmenu);
        View headerView = navigationView.getHeaderView(0);

        navUseremail = headerView.findViewById(R.id.nav_User_email);
        navUsername = headerView.findViewById(R.id.nav_User_name);
        String userID = firebaseUser.getUid();

        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered User");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if (readUserDetails != null) {
                    fullname = readUserDetails.Fullname;
                    email = firebaseUser.getEmail();


                    navUsername.setText(fullname);
                    navUseremail.setText(email);

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                //Toast.makeText(CustomerProfileActivity.this, "Something went wrong! User's details are not available at this moment", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public String Add(String a,String b)
    {
        String bb=a.concat(b);
        return bb;
    }

    public String Add(String a,String b,String c)
    {
        String bb=a.concat(b.concat(c));
        return bb;
    }

}