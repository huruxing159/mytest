delete from databasechangelog where id in('beta1/sequence-rollback.xml','beta1/sequence.xml',
'beta1/users-rollback.xml','beta1/users.xml',
'beta1/roles-rollback.xml','beta1/roles.xml',
'beta1/users_roles-rollback.xml','beta1/users_roles.xml',
'beta1/authorities-rollback.xml','beta1/authorities.xml',
'beta1/roles_authorities-rollback.xml','beta1/roles_authorities.xml',
'beta1/data/add_role-rollback.xml','beta1/data/add_role.xml',
'beta1/data/add_authority-rollback.xml','beta1/data/add_authority.xml',
'beta1/purchase_attemps.xml','beta1/purchase_attemps-rollback.xml',
'beta1/orders.xml','beta1/orders-rollback.xml',
'beta1/countries.xml','beta1/countries-rollback.xml',
'beta1/currencies.xml','beta1/currencies-rollback.xml',
'beta1/data/add_country.xml','beta1/data/add_country-rollback.xml',
'beta1/data/add_currency.xml','beta1/data/add_currency-rollback.xml',
'beta1/servers.xml','beta1/servers-rollback.xml',
'beta1/vendors.xml','beta1/vendors-rollback.xml',
'beta1/vendor_users.xml','beta1/vendor_users-rollback.xml',
'beta1/storagelists.xml','beta1/storagelists-rollback.xml',
'beta1/orderrecords.xml','beta1/orderrecords-rollback.xml',
'beta1/orderpictures.xml','beta1/orderpictures-rollback.xml',
'beta1/paypal_records.xml','beta1/paypal_records-rollback.xml',
'beta1/moneybookers_records.xml','beta1/moneybookers_records-rollback.xml',
'beta1/inform_backoffices.xml','beta1/inform_backoffices-rollback.xml',
'beta1/data/add_users.xml','beta1/data/add_users-rollback.xml',
'beta1/timerwatch.xml','beta1/timerwatch-rollback.xml',
'beta1/orderfailreasons.xml','beta1/orderfailreasons-rollback.xml') ;