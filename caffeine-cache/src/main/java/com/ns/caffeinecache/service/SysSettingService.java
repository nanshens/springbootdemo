package com.ns.caffeinecache.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.ns.caffeinecache.entity.SysSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ns
 * @create 2020-09-20
 */
@Service
public class SysSettingService {
    private Map<String, SysSetting> sysSettings = new HashMap<>();

    @Autowired private Cache<String, SysSetting> sysSettingCache;

    {
        sysSettings.put("1", new SysSetting("1", true, "email1", "ad1", LocalDate.now()));
        sysSettings.put("2", new SysSetting("2", true, "email2", "ad2", LocalDate.now()));
        sysSettings.put("3", new SysSetting("3", true, "email3", "ad3", LocalDate.now()));
        sysSettings.put("4", new SysSetting("4", true, "email4", "ad4", LocalDate.now()));
        sysSettings.put("5", new SysSetting("5", true, "email5", "ad5", LocalDate.now()));
    }

    public void initCache() {
        sysSettingCache.putAll(sysSettings);
    }

    public void create(SysSetting sysSetting) {
        sysSettings.put(sysSetting.getName(), sysSetting);
        sysSettingCache.put(sysSetting.getName(), sysSetting);
    }

    public SysSetting getByName(String name) {
        System.out.println("SysSetting by cache");
        SysSetting sysSetting = sysSettingCache.getIfPresent(name);
        if (sysSetting != null) return sysSetting;
        System.out.println("SysSetting by database");
        sysSetting = sysSettings.get(name);
        sysSettingCache.put(sysSetting.getName(), sysSetting);
        return sysSetting;
    }

    public void deleteAll() {
        sysSettings = new HashMap<>();
        sysSettingCache.cleanUp();
    }

    public Map<String, SysSetting> getAll() {
        Map<String, SysSetting> setting = sysSettingCache.asMap();
        if (setting.size() > 1) {
            return setting;
        } else {
            System.out.println("SysSetting by database");
            sysSettingCache.putAll(sysSettings);
            return sysSettings;
        }
    }
}
