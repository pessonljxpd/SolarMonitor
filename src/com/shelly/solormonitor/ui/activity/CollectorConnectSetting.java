package com.shelly.solormonitor.ui.activity;

import org.androidannotations.annotations.EActivity;

import com.ty.solarmonitor.R;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;

@EActivity
public class CollectorConnectSetting extends PreferenceActivity implements OnSharedPreferenceChangeListener {

	public static final String IP_ADDRESS_PREFERENCE = "IpAddress";
	public static final String PORT_PREFERENCE = "PortSetting";
	public static final String POLL_TIME_PREFERENCE = "PollTime";
	public static final String SLAVE_ID = "SlaveAddress";

	private EditTextPreference ipAddressPreference;
	private EditTextPreference portPreference;
	private EditTextPreference pollTimePreference;
	private EditTextPreference slaveId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.collector_preferences);
		init();
	}

	private void init() {
		ipAddressPreference = (EditTextPreference) getPreferenceScreen().findPreference(IP_ADDRESS_PREFERENCE);
		ipAddressPreference.setSummary(getPreferenceScreen().getSharedPreferences().getString(IP_ADDRESS_PREFERENCE,
				"Set a new ip address"));
		portPreference = (EditTextPreference) getPreferenceScreen().findPreference(PORT_PREFERENCE);
		portPreference.setSummary(getPreferenceScreen().getSharedPreferences().getString(PORT_PREFERENCE,
				"Set a destination Port (default = 502)"));
		pollTimePreference = (EditTextPreference) getPreferenceScreen().findPreference(POLL_TIME_PREFERENCE);
		pollTimePreference.setSummary(getPreferenceScreen().getSharedPreferences().getString(POLL_TIME_PREFERENCE,
				"Set Poll Time in ms (0 = Poll Once)")
				+ "ms");
		slaveId = (EditTextPreference) getPreferenceScreen().findPreference(SLAVE_ID);

	}

	@Override
	protected void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(IP_ADDRESS_PREFERENCE)) {
			ipAddressPreference.setSummary(sharedPreferences.getString(key, "Set a new ip address"));
		} else if (key.equals(portPreference)) {
			portPreference.setSummary(sharedPreferences.getString(key, "Set a destination Port (default = 502)"));
		} else if (key.equals(pollTimePreference)) {
			pollTimePreference.setSummary(sharedPreferences.getString(key, "Set Poll Time in ms (0 = Poll Once)")+"ms");
		} else if (key.equals(SLAVE_ID)) {
			slaveId.setSummary(sharedPreferences.getString(key, "Set Alternative Slave Address (default = 1)"));
		}
	}

}
