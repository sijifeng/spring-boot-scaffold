SELECT log_systime
  ip,
KEY,
TIME,
app,
req_time,
uuid,
uuid_md5,
uid,
username,
role,
SERVER,
role_name,
role_id,
money,
ext_activation_mac,
ext_activation_ip,
ext_activation_os_name,
ext_activation_os_version,
ext_activation_app_uuid,
ext_reg_mac,
ext_reg_ip,
ext_reg_os_name,
ext_reg_os_version,
ext_reg_app_uuid,
token,
ds
FROM
( SELECT
t1.*
, (t1.time-t2.timestamp) AS time_diff
, t2.ip
FROM
( SELECT
*
FROM
odl_advqmqj_sdk_xyplat WHERE ds='2017-05-04' AND KEY ='activation') t1
LEFT OUTER JOIN
( SELECT * FROM odl_advqmqj_view_xyplat WHERE ds='2017-05-04') t2
ON t1.ip=t2.ip AND (t1.time-t2.timestamp) > 10 AND (t1.time-t2.timestamp)<5313) t1
GROUP BY log_systime, ip, KEY, TIME, app, req_time, uuid, uuid_md5, uid, username, role, SERVER, role_name, role_id,
money, ext_activation_mac, ext_activation_ip, ext_activation_os_name, ext_activation_os_version, ext_activation_app_uuid,
ext_reg_mac, ext_reg_ip, ext_reg_os_name, ext_reg_os_version, ext_reg_app_uuid, token, ds