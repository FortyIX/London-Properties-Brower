

# Classifier for Properties type
This is a classifier for identify the type of the property according to the given condtions, Weka was used as the ML Engine
```
algorithm : J48 (Implemation of C4.5)
```
<br/>

## Built Decision Tree
```
J48 pruned tree
------------------

price <= 60
|   price <= 45
|   |   price <= 20
|   |   |   availability_365 <= 364
|   |   |   |   price <= 17
|   |   |   |   |   price <= 13
|   |   |   |   |   |   price <= 11
|   |   |   |   |   |   |   availability_365 <= 242: Private room (21.0/8.0)
|   |   |   |   |   |   |   availability_365 > 242: Shared room (2.0)
|   |   |   |   |   |   price > 11
|   |   |   |   |   |   |   minimum_nights <= 2: Shared room (27.0/1.0)
|   |   |   |   |   |   |   minimum_nights > 2
|   |   |   |   |   |   |   |   price <= 12: Private room (2.0)
|   |   |   |   |   |   |   |   price > 12: Shared room (4.0/1.0)
|   |   |   |   |   price > 13
|   |   |   |   |   |   neighbourhood <= 30
|   |   |   |   |   |   |   price <= 14
|   |   |   |   |   |   |   |   availability_365 <= 134: Private room (7.0/1.0)
|   |   |   |   |   |   |   |   availability_365 > 134: Shared room (7.0/1.0)
|   |   |   |   |   |   |   price > 14
|   |   |   |   |   |   |   |   neighbourhood <= 28
|   |   |   |   |   |   |   |   |   neighbourhood <= 21: Private room (112.0/26.0)
|   |   |   |   |   |   |   |   |   neighbourhood > 21
|   |   |   |   |   |   |   |   |   |   minimum_nights <= 1: Shared room (17.0/5.0)
|   |   |   |   |   |   |   |   |   |   minimum_nights > 1
|   |   |   |   |   |   |   |   |   |   |   price <= 15: Private room (3.0)
|   |   |   |   |   |   |   |   |   |   |   price > 15: Shared room (3.0/1.0)
|   |   |   |   |   |   |   |   neighbourhood > 28: Private room (12.0/1.0)
|   |   |   |   |   |   neighbourhood > 30
|   |   |   |   |   |   |   minimum_nights <= 1
|   |   |   |   |   |   |   |   price <= 14: Private room (2.0)
|   |   |   |   |   |   |   |   price > 14: Shared room (23.0/2.0)
|   |   |   |   |   |   |   minimum_nights > 1: Private room (8.0/2.0)
|   |   |   |   price > 17
|   |   |   |   |   neighbourhood <= 21: Private room (343.0/28.0)
|   |   |   |   |   neighbourhood > 21
|   |   |   |   |   |   availability_365 <= 1: Private room (135.0/15.0)
|   |   |   |   |   |   availability_365 > 1
|   |   |   |   |   |   |   minimum_nights <= 2
|   |   |   |   |   |   |   |   price <= 19
|   |   |   |   |   |   |   |   |   neighbourhood <= 31
|   |   |   |   |   |   |   |   |   |   price <= 18
|   |   |   |   |   |   |   |   |   |   |   availability_365 <= 89: Private room (6.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   availability_365 > 89: Shared room (14.0/2.0)
|   |   |   |   |   |   |   |   |   |   price > 18
|   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 22: Shared room (2.0)
|   |   |   |   |   |   |   |   |   |   |   neighbourhood > 22
|   |   |   |   |   |   |   |   |   |   |   |   availability_365 <= 218: Private room (6.0)
|   |   |   |   |   |   |   |   |   |   |   |   availability_365 > 218: Shared room (3.0/1.0)
|   |   |   |   |   |   |   |   |   neighbourhood > 31: Shared room (12.0)
|   |   |   |   |   |   |   |   price > 19
|   |   |   |   |   |   |   |   |   neighbourhood <= 24: Private room (22.0/4.0)
|   |   |   |   |   |   |   |   |   neighbourhood > 24
|   |   |   |   |   |   |   |   |   |   neighbourhood <= 26: Shared room (11.0/1.0)
|   |   |   |   |   |   |   |   |   |   neighbourhood > 26
|   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 27: Private room (6.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   neighbourhood > 27
|   |   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 29
|   |   |   |   |   |   |   |   |   |   |   |   |   availability_365 <= 240: Private room (2.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   |   |   availability_365 > 240: Shared room (6.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   |   neighbourhood > 29: Private room (18.0/6.0)
|   |   |   |   |   |   |   minimum_nights > 2: Private room (31.0/1.0)
|   |   |   availability_365 > 364
|   |   |   |   price <= 19: Shared room (91.0/13.0)
|   |   |   |   price > 19: Private room (20.0/5.0)
|   |   price > 20
|   |   |   price <= 34
|   |   |   |   price <= 26
|   |   |   |   |   neighbourhood <= 21: Private room (1235.0/41.0)
|   |   |   |   |   neighbourhood > 21
|   |   |   |   |   |   minimum_nights <= 2
|   |   |   |   |   |   |   availability_365 <= 72: Private room (297.0/19.0)
|   |   |   |   |   |   |   availability_365 > 72
|   |   |   |   |   |   |   |   neighbourhood <= 30
|   |   |   |   |   |   |   |   |   neighbourhood <= 27
|   |   |   |   |   |   |   |   |   |   neighbourhood <= 26
|   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 25
|   |   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 22
|   |   |   |   |   |   |   |   |   |   |   |   |   availability_365 <= 332
|   |   |   |   |   |   |   |   |   |   |   |   |   |   price <= 25: Private room (17.0/2.0)
|   |   |   |   |   |   |   |   |   |   |   |   |   |   price > 25: Shared room (3.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   |   |   availability_365 > 332
|   |   |   |   |   |   |   |   |   |   |   |   |   |   availability_365 <= 364
|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   price <= 25: Shared room (11.0)
|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   price > 25: Private room (3.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   |   |   |   availability_365 > 364: Private room (6.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   |   neighbourhood > 22: Private room (67.0/6.0)
|   |   |   |   |   |   |   |   |   |   |   neighbourhood > 25
|   |   |   |   |   |   |   |   |   |   |   |   availability_365 <= 171: Private room (3.0)
|   |   |   |   |   |   |   |   |   |   |   |   availability_365 > 171: Shared room (16.0/2.0)
|   |   |   |   |   |   |   |   |   |   neighbourhood > 26: Private room (31.0/1.0)
|   |   |   |   |   |   |   |   |   neighbourhood > 27
|   |   |   |   |   |   |   |   |   |   price <= 22: Shared room (5.0)
|   |   |   |   |   |   |   |   |   |   price > 22
|   |   |   |   |   |   |   |   |   |   |   availability_365 <= 341
|   |   |   |   |   |   |   |   |   |   |   |   availability_365 <= 94: Shared room (4.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   |   availability_365 > 94: Private room (11.0/2.0)
|   |   |   |   |   |   |   |   |   |   |   availability_365 > 341
|   |   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 29
|   |   |   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 28: Shared room (3.0)
|   |   |   |   |   |   |   |   |   |   |   |   |   neighbourhood > 28: Private room (3.0)
|   |   |   |   |   |   |   |   |   |   |   |   neighbourhood > 29: Shared room (6.0)
|   |   |   |   |   |   |   |   neighbourhood > 30: Private room (107.0/15.0)
|   |   |   |   |   |   minimum_nights > 2: Private room (359.0/7.0)
|   |   |   |   price > 26: Private room (4193.0/123.0)
|   |   |   price > 34: Private room (8325.0/442.0)
|   price > 45
|   |   minimum_nights <= 2
|   |   |   availability_365 <= 68
|   |   |   |   neighbourhood <= 21
|   |   |   |   |   price <= 50
|   |   |   |   |   |   minimum_nights <= 1: Private room (136.0/29.0)
|   |   |   |   |   |   minimum_nights > 1
|   |   |   |   |   |   |   neighbourhood <= 19: Private room (43.0/15.0)
|   |   |   |   |   |   |   neighbourhood > 19
|   |   |   |   |   |   |   |   price <= 49: Entire home/apt (6.0)
|   |   |   |   |   |   |   |   price > 49
|   |   |   |   |   |   |   |   |   availability_365 <= 8: Entire home/apt (5.0/1.0)
|   |   |   |   |   |   |   |   |   availability_365 > 8: Private room (4.0/1.0)
|   |   |   |   |   price > 50
|   |   |   |   |   |   minimum_nights <= 1
|   |   |   |   |   |   |   neighbourhood <= 19: Private room (113.0/47.0)
|   |   |   |   |   |   |   neighbourhood > 19: Entire home/apt (25.0/6.0)
|   |   |   |   |   |   minimum_nights > 1: Entire home/apt (89.0/26.0)
|   |   |   |   neighbourhood > 21
|   |   |   |   |   price <= 55
|   |   |   |   |   |   availability_365 <= 61: Private room (1033.0/141.0)
|   |   |   |   |   |   availability_365 > 61
|   |   |   |   |   |   |   price <= 51: Private room (53.0/11.0)
|   |   |   |   |   |   |   price > 51
|   |   |   |   |   |   |   |   minimum_nights <= 1
|   |   |   |   |   |   |   |   |   availability_365 <= 62: Entire home/apt (7.0)
|   |   |   |   |   |   |   |   |   availability_365 > 62: Private room (4.0/1.0)
|   |   |   |   |   |   |   |   minimum_nights > 1: Private room (8.0/2.0)
|   |   |   |   |   price > 55
|   |   |   |   |   |   minimum_nights <= 1
|   |   |   |   |   |   |   price <= 57
|   |   |   |   |   |   |   |   availability_365 <= 4: Private room (12.0)
|   |   |   |   |   |   |   |   availability_365 > 4
|   |   |   |   |   |   |   |   |   neighbourhood <= 31
|   |   |   |   |   |   |   |   |   |   neighbourhood <= 29
|   |   |   |   |   |   |   |   |   |   |   availability_365 <= 36: Private room (3.0)
|   |   |   |   |   |   |   |   |   |   |   availability_365 > 36: Entire home/apt (8.0/3.0)
|   |   |   |   |   |   |   |   |   |   neighbourhood > 29: Entire home/apt (7.0)
|   |   |   |   |   |   |   |   |   neighbourhood > 31: Private room (2.0)
|   |   |   |   |   |   |   price > 57: Private room (287.0/71.0)
|   |   |   |   |   |   minimum_nights > 1
|   |   |   |   |   |   |   neighbourhood <= 23
|   |   |   |   |   |   |   |   neighbourhood <= 22
|   |   |   |   |   |   |   |   |   availability_365 <= 12: Entire home/apt (12.0/5.0)
|   |   |   |   |   |   |   |   |   availability_365 > 12: Private room (6.0)
|   |   |   |   |   |   |   |   neighbourhood > 22: Entire home/apt (23.0/7.0)
|   |   |   |   |   |   |   neighbourhood > 23
|   |   |   |   |   |   |   |   neighbourhood <= 30: Private room (102.0/30.0)
|   |   |   |   |   |   |   |   neighbourhood > 30
|   |   |   |   |   |   |   |   |   availability_365 <= 45
|   |   |   |   |   |   |   |   |   |   neighbourhood <= 31
|   |   |   |   |   |   |   |   |   |   |   availability_365 <= 2
|   |   |   |   |   |   |   |   |   |   |   |   price <= 59: Entire home/apt (8.0/2.0)
|   |   |   |   |   |   |   |   |   |   |   |   price > 59: Private room (6.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   availability_365 > 2: Entire home/apt (11.0/1.0)
|   |   |   |   |   |   |   |   |   |   neighbourhood > 31: Private room (17.0/5.0)
|   |   |   |   |   |   |   |   |   availability_365 > 45: Private room (12.0)
|   |   |   availability_365 > 68: Private room (3704.0/360.0)
|   |   minimum_nights > 2
|   |   |   neighbourhood <= 21
|   |   |   |   availability_365 <= 359
|   |   |   |   |   price <= 50
|   |   |   |   |   |   neighbourhood <= 13
|   |   |   |   |   |   |   neighbourhood <= 3: Entire home/apt (12.0/2.0)
|   |   |   |   |   |   |   neighbourhood > 3: Private room (90.0/34.0)
|   |   |   |   |   |   neighbourhood > 13
|   |   |   |   |   |   |   minimum_nights <= 5
|   |   |   |   |   |   |   |   neighbourhood <= 20: Entire home/apt (30.0/2.0)
|   |   |   |   |   |   |   |   neighbourhood > 20
|   |   |   |   |   |   |   |   |   availability_365 <= 86: Entire home/apt (12.0/2.0)
|   |   |   |   |   |   |   |   |   availability_365 > 86: Private room (7.0/1.0)
|   |   |   |   |   |   |   minimum_nights > 5
|   |   |   |   |   |   |   |   neighbourhood <= 20: Private room (15.0/5.0)
|   |   |   |   |   |   |   |   neighbourhood > 20: Entire home/apt (8.0/2.0)
|   |   |   |   |   price > 50
|   |   |   |   |   |   minimum_nights <= 4
|   |   |   |   |   |   |   availability_365 <= 63: Entire home/apt (72.0/8.0)
|   |   |   |   |   |   |   availability_365 > 63
|   |   |   |   |   |   |   |   availability_365 <= 90
|   |   |   |   |   |   |   |   |   price <= 57: Private room (7.0)
|   |   |   |   |   |   |   |   |   price > 57
|   |   |   |   |   |   |   |   |   |   availability_365 <= 89: Entire home/apt (4.0/1.0)
|   |   |   |   |   |   |   |   |   |   availability_365 > 89: Private room (2.0)
|   |   |   |   |   |   |   |   availability_365 > 90
|   |   |   |   |   |   |   |   |   neighbourhood <= 5
|   |   |   |   |   |   |   |   |   |   neighbourhood <= 1: Entire home/apt (3.0)
|   |   |   |   |   |   |   |   |   |   neighbourhood > 1
|   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 3: Private room (5.0)
|   |   |   |   |   |   |   |   |   |   |   neighbourhood > 3
|   |   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 4: Entire home/apt (2.0)
|   |   |   |   |   |   |   |   |   |   |   |   neighbourhood > 4: Private room (6.0/2.0)
|   |   |   |   |   |   |   |   |   neighbourhood > 5
|   |   |   |   |   |   |   |   |   |   neighbourhood <= 19: Entire home/apt (59.0/9.0)
|   |   |   |   |   |   |   |   |   |   neighbourhood > 19
|   |   |   |   |   |   |   |   |   |   |   availability_365 <= 177: Entire home/apt (2.0)
|   |   |   |   |   |   |   |   |   |   |   availability_365 > 177: Private room (8.0/2.0)
|   |   |   |   |   |   minimum_nights > 4: Entire home/apt (146.0/10.0)
|   |   |   |   availability_365 > 359
|   |   |   |   |   neighbourhood <= 6: Private room (20.0/2.0)
|   |   |   |   |   neighbourhood > 6
|   |   |   |   |   |   neighbourhood <= 18
|   |   |   |   |   |   |   neighbourhood <= 13
|   |   |   |   |   |   |   |   minimum_nights <= 3
|   |   |   |   |   |   |   |   |   neighbourhood <= 9
|   |   |   |   |   |   |   |   |   |   price <= 57: Private room (4.0/2.0)
|   |   |   |   |   |   |   |   |   |   price > 57: Entire home/apt (2.0)
|   |   |   |   |   |   |   |   |   neighbourhood > 9: Private room (15.0/1.0)
|   |   |   |   |   |   |   |   minimum_nights > 3
|   |   |   |   |   |   |   |   |   price <= 55: Private room (15.0/6.0)
|   |   |   |   |   |   |   |   |   price > 55: Entire home/apt (8.0/1.0)
|   |   |   |   |   |   |   neighbourhood > 13
|   |   |   |   |   |   |   |   minimum_nights <= 10: Entire home/apt (28.0/6.0)
|   |   |   |   |   |   |   |   minimum_nights > 10: Private room (3.0/1.0)
|   |   |   |   |   |   neighbourhood > 18: Private room (13.0/1.0)
|   |   |   neighbourhood > 21
|   |   |   |   availability_365 <= 67
|   |   |   |   |   price <= 55
|   |   |   |   |   |   neighbourhood <= 24
|   |   |   |   |   |   |   minimum_nights <= 3
|   |   |   |   |   |   |   |   price <= 50
|   |   |   |   |   |   |   |   |   neighbourhood <= 23
|   |   |   |   |   |   |   |   |   |   availability_365 <= 5: Private room (18.0/5.0)
|   |   |   |   |   |   |   |   |   |   availability_365 > 5: Entire home/apt (6.0/2.0)
|   |   |   |   |   |   |   |   |   neighbourhood > 23: Entire home/apt (4.0/1.0)
|   |   |   |   |   |   |   |   price > 50: Private room (26.0/3.0)
|   |   |   |   |   |   |   minimum_nights > 3: Entire home/apt (57.0/26.0)
|   |   |   |   |   |   neighbourhood > 24: Private room (303.0/77.0)
|   |   |   |   |   price > 55
|   |   |   |   |   |   neighbourhood <= 28
|   |   |   |   |   |   |   neighbourhood <= 24
|   |   |   |   |   |   |   |   minimum_nights <= 8
|   |   |   |   |   |   |   |   |   availability_365 <= 16
|   |   |   |   |   |   |   |   |   |   neighbourhood <= 22
|   |   |   |   |   |   |   |   |   |   |   availability_365 <= 6: Private room (7.0/2.0)
|   |   |   |   |   |   |   |   |   |   |   availability_365 > 6: Entire home/apt (2.0)
|   |   |   |   |   |   |   |   |   |   neighbourhood > 22
|   |   |   |   |   |   |   |   |   |   |   price <= 59: Entire home/apt (8.0)
|   |   |   |   |   |   |   |   |   |   |   price > 59
|   |   |   |   |   |   |   |   |   |   |   |   minimum_nights <= 4: Entire home/apt (9.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   |   minimum_nights > 4: Private room (6.0/1.0)
|   |   |   |   |   |   |   |   |   availability_365 > 16
|   |   |   |   |   |   |   |   |   |   availability_365 <= 57: Private room (12.0/2.0)
|   |   |   |   |   |   |   |   |   |   availability_365 > 57: Entire home/apt (3.0/1.0)
|   |   |   |   |   |   |   |   minimum_nights > 8: Entire home/apt (9.0/2.0)
|   |   |   |   |   |   |   neighbourhood > 24
|   |   |   |   |   |   |   |   availability_365 <= 15: Private room (28.0/4.0)
|   |   |   |   |   |   |   |   availability_365 > 15
|   |   |   |   |   |   |   |   |   neighbourhood <= 27
|   |   |   |   |   |   |   |   |   |   minimum_nights <= 4: Entire home/apt (5.0)
|   |   |   |   |   |   |   |   |   |   minimum_nights > 4: Private room (3.0/1.0)
|   |   |   |   |   |   |   |   |   neighbourhood > 27: Private room (6.0/1.0)
|   |   |   |   |   |   neighbourhood > 28: Entire home/apt (101.0/36.0)
|   |   |   |   availability_365 > 67: Private room (568.0/99.0)
price > 60
|   price <= 80
|   |   availability_365 <= 360
|   |   |   minimum_nights <= 1
|   |   |   |   availability_365 <= 72
|   |   |   |   |   availability_365 <= 59
|   |   |   |   |   |   price <= 70
|   |   |   |   |   |   |   neighbourhood <= 23: Entire home/apt (185.0/74.0)
|   |   |   |   |   |   |   neighbourhood > 23
|   |   |   |   |   |   |   |   neighbourhood <= 28: Private room (157.0/59.0)
|   |   |   |   |   |   |   |   neighbourhood > 28
|   |   |   |   |   |   |   |   |   price <= 63: Entire home/apt (23.0/5.0)
|   |   |   |   |   |   |   |   |   price > 63
|   |   |   |   |   |   |   |   |   |   availability_365 <= 38
|   |   |   |   |   |   |   |   |   |   |   availability_365 <= 30: Private room (149.0/72.0)
|   |   |   |   |   |   |   |   |   |   |   availability_365 > 30: Entire home/apt (7.0/2.0)
|   |   |   |   |   |   |   |   |   |   availability_365 > 38: Private room (11.0/1.0)
|   |   |   |   |   |   price > 70: Entire home/apt (497.0/176.0)
|   |   |   |   |   availability_365 > 59
|   |   |   |   |   |   availability_365 <= 65: Entire home/apt (136.0/19.0)
|   |   |   |   |   |   availability_365 > 65
|   |   |   |   |   |   |   neighbourhood <= 20: Entire home/apt (6.0)
|   |   |   |   |   |   |   neighbourhood > 20
|   |   |   |   |   |   |   |   availability_365 <= 68: Private room (18.0/5.0)
|   |   |   |   |   |   |   |   availability_365 > 68
|   |   |   |   |   |   |   |   |   neighbourhood <= 24: Entire home/apt (5.0)
|   |   |   |   |   |   |   |   |   neighbourhood > 24
|   |   |   |   |   |   |   |   |   |   neighbourhood <= 25: Private room (2.0)
|   |   |   |   |   |   |   |   |   |   neighbourhood > 25
|   |   |   |   |   |   |   |   |   |   |   price <= 71
|   |   |   |   |   |   |   |   |   |   |   |   price <= 69: Entire home/apt (2.0)
|   |   |   |   |   |   |   |   |   |   |   |   price > 69: Private room (5.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   price > 71: Entire home/apt (5.0)
|   |   |   |   availability_365 > 72
|   |   |   |   |   price <= 66
|   |   |   |   |   |   neighbourhood <= 19
|   |   |   |   |   |   |   neighbourhood <= 6
|   |   |   |   |   |   |   |   neighbourhood <= 2: Entire home/apt (3.0)
|   |   |   |   |   |   |   |   neighbourhood > 2: Private room (23.0/3.0)
|   |   |   |   |   |   |   neighbourhood > 6: Entire home/apt (39.0/17.0)
|   |   |   |   |   |   neighbourhood > 19
|   |   |   |   |   |   |   neighbourhood <= 28: Private room (143.0/17.0)
|   |   |   |   |   |   |   neighbourhood > 28
|   |   |   |   |   |   |   |   price <= 63
|   |   |   |   |   |   |   |   |   price <= 61: Private room (4.0)
|   |   |   |   |   |   |   |   |   price > 61
|   |   |   |   |   |   |   |   |   |   neighbourhood <= 29: Entire home/apt (5.0)
|   |   |   |   |   |   |   |   |   |   neighbourhood > 29
|   |   |   |   |   |   |   |   |   |   |   availability_365 <= 81: Entire home/apt (2.0)
|   |   |   |   |   |   |   |   |   |   |   availability_365 > 81: Private room (8.0/1.0)
|   |   |   |   |   |   |   |   price > 63: Private room (75.0/15.0)
|   |   |   |   |   price > 66
|   |   |   |   |   |   neighbourhood <= 28
|   |   |   |   |   |   |   neighbourhood <= 18
|   |   |   |   |   |   |   |   neighbourhood <= 1: Entire home/apt (10.0)
|   |   |   |   |   |   |   |   neighbourhood > 1
|   |   |   |   |   |   |   |   |   neighbourhood <= 5
|   |   |   |   |   |   |   |   |   |   availability_365 <= 110: Entire home/apt (6.0/1.0)
|   |   |   |   |   |   |   |   |   |   availability_365 > 110
|   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 3
|   |   |   |   |   |   |   |   |   |   |   |   availability_365 <= 157: Entire home/apt (2.0)
|   |   |   |   |   |   |   |   |   |   |   |   availability_365 > 157: Private room (7.0/2.0)
|   |   |   |   |   |   |   |   |   |   |   neighbourhood > 3: Private room (18.0/1.0)
|   |   |   |   |   |   |   |   |   neighbourhood > 5
|   |   |   |   |   |   |   |   |   |   availability_365 <= 100
|   |   |   |   |   |   |   |   |   |   |   availability_365 <= 85
|   |   |   |   |   |   |   |   |   |   |   |   price <= 71: Private room (2.0)
|   |   |   |   |   |   |   |   |   |   |   |   price > 71: Entire home/apt (6.0)
|   |   |   |   |   |   |   |   |   |   |   availability_365 > 85: Private room (21.0/2.0)
|   |   |   |   |   |   |   |   |   |   availability_365 > 100: Entire home/apt (85.0/28.0)
|   |   |   |   |   |   |   neighbourhood > 18
|   |   |   |   |   |   |   |   price <= 70
|   |   |   |   |   |   |   |   |   price <= 69
|   |   |   |   |   |   |   |   |   |   availability_365 <= 89: Private room (6.0)
|   |   |   |   |   |   |   |   |   |   availability_365 > 89
|   |   |   |   |   |   |   |   |   |   |   availability_365 <= 172: Entire home/apt (14.0/5.0)
|   |   |   |   |   |   |   |   |   |   |   availability_365 > 172
|   |   |   |   |   |   |   |   |   |   |   |   price <= 68: Private room (18.0/3.0)
|   |   |   |   |   |   |   |   |   |   |   |   price > 68
|   |   |   |   |   |   |   |   |   |   |   |   |   availability_365 <= 232: Private room (7.0)
|   |   |   |   |   |   |   |   |   |   |   |   |   availability_365 > 232: Entire home/apt (21.0/6.0)
|   |   |   |   |   |   |   |   |   price > 69: Private room (125.0/25.0)
|   |   |   |   |   |   |   |   price > 70
|   |   |   |   |   |   |   |   |   neighbourhood <= 20
|   |   |   |   |   |   |   |   |   |   availability_365 <= 346
|   |   |   |   |   |   |   |   |   |   |   availability_365 <= 207: Private room (11.0/2.0)
|   |   |   |   |   |   |   |   |   |   |   availability_365 > 207: Entire home/apt (6.0)
|   |   |   |   |   |   |   |   |   |   availability_365 > 346: Private room (8.0)
|   |   |   |   |   |   |   |   |   neighbourhood > 20
|   |   |   |   |   |   |   |   |   |   neighbourhood <= 21: Entire home/apt (13.0/3.0)
|   |   |   |   |   |   |   |   |   |   neighbourhood > 21: Private room (261.0/105.0)
|   |   |   |   |   |   neighbourhood > 28
|   |   |   |   |   |   |   availability_365 <= 343
|   |   |   |   |   |   |   |   availability_365 <= 90: Private room (60.0/25.0)
|   |   |   |   |   |   |   |   availability_365 > 90
|   |   |   |   |   |   |   |   |   price <= 69
|   |   |   |   |   |   |   |   |   |   neighbourhood <= 29
|   |   |   |   |   |   |   |   |   |   |   price <= 68: Private room (2.0)
|   |   |   |   |   |   |   |   |   |   |   price > 68: Entire home/apt (25.0/2.0)
|   |   |   |   |   |   |   |   |   |   neighbourhood > 29
|   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 30: Private room (5.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   neighbourhood > 30
|   |   |   |   |   |   |   |   |   |   |   |   availability_365 <= 261: Entire home/apt (7.0)
|   |   |   |   |   |   |   |   |   |   |   |   availability_365 > 261: Private room (6.0/2.0)
|   |   |   |   |   |   |   |   |   price > 69
|   |   |   |   |   |   |   |   |   |   price <= 70: Private room (39.0/14.0)
|   |   |   |   |   |   |   |   |   |   price > 70: Entire home/apt (124.0/39.0)
|   |   |   |   |   |   |   availability_365 > 343
|   |   |   |   |   |   |   |   neighbourhood <= 30: Private room (15.0/1.0)
|   |   |   |   |   |   |   |   neighbourhood > 30
|   |   |   |   |   |   |   |   |   availability_365 <= 356
|   |   |   |   |   |   |   |   |   |   neighbourhood <= 31
|   |   |   |   |   |   |   |   |   |   |   availability_365 <= 353: Entire home/apt (4.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   availability_365 > 353: Private room (2.0)
|   |   |   |   |   |   |   |   |   |   neighbourhood > 31: Private room (6.0/1.0)
|   |   |   |   |   |   |   |   |   availability_365 > 356: Entire home/apt (3.0)
|   |   |   minimum_nights > 1
|   |   |   |   availability_365 <= 78
|   |   |   |   |   neighbourhood <= 21: Entire home/apt (545.0/42.0)
|   |   |   |   |   neighbourhood > 21
|   |   |   |   |   |   price <= 70
|   |   |   |   |   |   |   minimum_nights <= 2
|   |   |   |   |   |   |   |   neighbourhood <= 28
|   |   |   |   |   |   |   |   |   neighbourhood <= 27
|   |   |   |   |   |   |   |   |   |   availability_365 <= 68
|   |   |   |   |   |   |   |   |   |   |   price <= 66
|   |   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 25: Private room (44.0/18.0)
|   |   |   |   |   |   |   |   |   |   |   |   neighbourhood > 25
|   |   |   |   |   |   |   |   |   |   |   |   |   availability_365 <= 34: Entire home/apt (9.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   |   |   availability_365 > 34: Private room (3.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   price > 66
|   |   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 24: Entire home/apt (57.0/13.0)
|   |   |   |   |   |   |   |   |   |   |   |   neighbourhood > 24
|   |   |   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 25: Private room (15.0/3.0)
|   |   |   |   |   |   |   |   |   |   |   |   |   neighbourhood > 25: Entire home/apt (14.0/5.0)
|   |   |   |   |   |   |   |   |   |   availability_365 > 68
|   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 22
|   |   |   |   |   |   |   |   |   |   |   |   price <= 69: Private room (2.0)
|   |   |   |   |   |   |   |   |   |   |   |   price > 69: Entire home/apt (2.0)
|   |   |   |   |   |   |   |   |   |   |   neighbourhood > 22: Private room (9.0)
|   |   |   |   |   |   |   |   |   neighbourhood > 27: Private room (24.0/5.0)
|   |   |   |   |   |   |   |   neighbourhood > 28: Entire home/apt (175.0/58.0)
|   |   |   |   |   |   |   minimum_nights > 2: Entire home/apt (439.0/107.0)
|   |   |   |   |   |   price > 70: Entire home/apt (1054.0/161.0)
|   |   |   |   availability_365 > 78
|   |   |   |   |   minimum_nights <= 3
|   |   |   |   |   |   price <= 73
|   |   |   |   |   |   |   neighbourhood <= 19
|   |   |   |   |   |   |   |   minimum_nights <= 2
|   |   |   |   |   |   |   |   |   price <= 70: Entire home/apt (74.0/29.0)
|   |   |   |   |   |   |   |   |   price > 70: Private room (4.0)
|   |   |   |   |   |   |   |   minimum_nights > 2
|   |   |   |   |   |   |   |   |   price <= 72
|   |   |   |   |   |   |   |   |   |   neighbourhood <= 10
|   |   |   |   |   |   |   |   |   |   |   price <= 68
|   |   |   |   |   |   |   |   |   |   |   |   availability_365 <= 166: Private room (4.0)
|   |   |   |   |   |   |   |   |   |   |   |   availability_365 > 166
|   |   |   |   |   |   |   |   |   |   |   |   |   availability_365 <= 277: Entire home/apt (3.0)
|   |   |   |   |   |   |   |   |   |   |   |   |   availability_365 > 277: Private room (6.0/2.0)
|   |   |   |   |   |   |   |   |   |   |   price > 68: Entire home/apt (9.0/2.0)
|   |   |   |   |   |   |   |   |   |   neighbourhood > 10: Entire home/apt (23.0/2.0)
|   |   |   |   |   |   |   |   |   price > 72: Private room (2.0)
|   |   |   |   |   |   |   neighbourhood > 19
|   |   |   |   |   |   |   |   neighbourhood <= 30: Private room (385.0/147.0)
|   |   |   |   |   |   |   |   neighbourhood > 30
|   |   |   |   |   |   |   |   |   neighbourhood <= 32
|   |   |   |   |   |   |   |   |   |   availability_365 <= 310: Entire home/apt (77.0/28.0)
|   |   |   |   |   |   |   |   |   |   availability_365 > 310
|   |   |   |   |   |   |   |   |   |   |   minimum_nights <= 2: Private room (22.0/5.0)
|   |   |   |   |   |   |   |   |   |   |   minimum_nights > 2: Entire home/apt (8.0/3.0)
|   |   |   |   |   |   |   |   |   neighbourhood > 32: Private room (2.0)
|   |   |   |   |   |   price > 73
|   |   |   |   |   |   |   availability_365 <= 90
|   |   |   |   |   |   |   |   minimum_nights <= 2
|   |   |   |   |   |   |   |   |   availability_365 <= 87
|   |   |   |   |   |   |   |   |   |   price <= 79
|   |   |   |   |   |   |   |   |   |   |   availability_365 <= 84: Private room (12.0/4.0)
|   |   |   |   |   |   |   |   |   |   |   availability_365 > 84: Entire home/apt (12.0/4.0)
|   |   |   |   |   |   |   |   |   |   price > 79: Entire home/apt (16.0/1.0)
|   |   |   |   |   |   |   |   |   availability_365 > 87: Private room (26.0/8.0)
|   |   |   |   |   |   |   |   minimum_nights > 2
|   |   |   |   |   |   |   |   |   price <= 79: Entire home/apt (10.0/4.0)
|   |   |   |   |   |   |   |   |   price > 79
|   |   |   |   |   |   |   |   |   |   availability_365 <= 88: Private room (4.0)
|   |   |   |   |   |   |   |   |   |   availability_365 > 88
|   |   |   |   |   |   |   |   |   |   |   availability_365 <= 89: Entire home/apt (2.0)
|   |   |   |   |   |   |   |   |   |   |   availability_365 > 89: Private room (3.0)
|   |   |   |   |   |   |   availability_365 > 90
|   |   |   |   |   |   |   |   minimum_nights <= 2
|   |   |   |   |   |   |   |   |   availability_365 <= 330: Entire home/apt (267.0/73.0)
|   |   |   |   |   |   |   |   |   availability_365 > 330
|   |   |   |   |   |   |   |   |   |   neighbourhood <= 20: Entire home/apt (8.0/1.0)
|   |   |   |   |   |   |   |   |   |   neighbourhood > 20
|   |   |   |   |   |   |   |   |   |   |   price <= 76: Private room (17.0/3.0)
|   |   |   |   |   |   |   |   |   |   |   price > 76
|   |   |   |   |   |   |   |   |   |   |   |   price <= 78: Entire home/apt (2.0)
|   |   |   |   |   |   |   |   |   |   |   |   price > 78
|   |   |   |   |   |   |   |   |   |   |   |   |   price <= 79: Private room (12.0/4.0)
|   |   |   |   |   |   |   |   |   |   |   |   |   price > 79
|   |   |   |   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 31
|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 30: Entire home/apt (15.0/6.0)
|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   neighbourhood > 30: Private room (7.0/1.0)
|   |   |   |   |   |   |   |   |   |   |   |   |   |   neighbourhood > 31: Entire home/apt (4.0)
|   |   |   |   |   |   |   |   minimum_nights > 2: Entire home/apt (160.0/29.0)
|   |   |   |   |   minimum_nights > 3
|   |   |   |   |   |   neighbourhood <= 21
|   |   |   |   |   |   |   price <= 68
|   |   |   |   |   |   |   |   availability_365 <= 182
|   |   |   |   |   |   |   |   |   neighbourhood <= 7: Private room (3.0)
|   |   |   |   |   |   |   |   |   neighbourhood > 7
|   |   |   |   |   |   |   |   |   |   minimum_nights <= 17: Entire home/apt (10.0/1.0)
|   |   |   |   |   |   |   |   |   |   minimum_nights > 17: Private room (4.0/1.0)
|   |   |   |   |   |   |   |   availability_365 > 182: Entire home/apt (70.0/1.0)
|   |   |   |   |   |   |   price > 68: Entire home/apt (86.0/3.0)
|   |   |   |   |   |   neighbourhood > 21
|   |   |   |   |   |   |   price <= 70
|   |   |   |   |   |   |   |   availability_365 <= 95: Private room (25.0/8.0)
|   |   |   |   |   |   |   |   availability_365 > 95
|   |   |   |   |   |   |   |   |   price <= 69
|   |   |   |   |   |   |   |   |   |   availability_365 <= 139: Entire home/apt (9.0)
|   |   |   |   |   |   |   |   |   |   availability_365 > 139
|   |   |   |   |   |   |   |   |   |   |   price <= 67: Entire home/apt (36.0/14.0)
|   |   |   |   |   |   |   |   |   |   |   price > 67: Private room (13.0/3.0)
|   |   |   |   |   |   |   |   |   price > 69: Entire home/apt (43.0/9.0)
|   |   |   |   |   |   |   price > 70: Entire home/apt (169.0/39.0)
|   |   availability_365 > 360
|   |   |   minimum_nights <= 2: Private room (890.0/132.0)
|   |   |   minimum_nights > 2
|   |   |   |   neighbourhood <= 21: Entire home/apt (82.0/31.0)
|   |   |   |   neighbourhood > 21
|   |   |   |   |   minimum_nights <= 3: Private room (68.0/14.0)
|   |   |   |   |   minimum_nights > 3
|   |   |   |   |   |   availability_365 <= 362: Entire home/apt (4.0/1.0)
|   |   |   |   |   |   availability_365 > 362
|   |   |   |   |   |   |   neighbourhood <= 25: Entire home/apt (15.0/5.0)
|   |   |   |   |   |   |   neighbourhood > 25: Private room (45.0/19.0)
|   price > 80
|   |   minimum_nights <= 1
|   |   |   availability_365 <= 361
|   |   |   |   price <= 109
|   |   |   |   |   availability_365 <= 72: Entire home/apt (1068.0/188.0)
|   |   |   |   |   availability_365 > 72
|   |   |   |   |   |   availability_365 <= 92
|   |   |   |   |   |   |   neighbourhood <= 21: Entire home/apt (38.0/14.0)
|   |   |   |   |   |   |   neighbourhood > 21
|   |   |   |   |   |   |   |   availability_365 <= 86
|   |   |   |   |   |   |   |   |   price <= 88
|   |   |   |   |   |   |   |   |   |   price <= 84: Entire home/apt (2.0)
|   |   |   |   |   |   |   |   |   |   price > 84: Private room (22.0/4.0)
|   |   |   |   |   |   |   |   |   price > 88: Entire home/apt (50.0/18.0)
|   |   |   |   |   |   |   |   availability_365 > 86: Private room (51.0/13.0)
|   |   |   |   |   |   availability_365 > 92
|   |   |   |   |   |   |   price <= 89
|   |   |   |   |   |   |   |   neighbourhood <= 29
|   |   |   |   |   |   |   |   |   availability_365 <= 129: Entire home/apt (9.0)
|   |   |   |   |   |   |   |   |   availability_365 > 129: Private room (174.0/83.0)
|   |   |   |   |   |   |   |   neighbourhood > 29
|   |   |   |   |   |   |   |   |   availability_365 <= 331: Entire home/apt (46.0/4.0)
|   |   |   |   |   |   |   |   |   availability_365 > 331
|   |   |   |   |   |   |   |   |   |   availability_365 <= 338: Private room (4.0)
|   |   |   |   |   |   |   |   |   |   availability_365 > 338
|   |   |   |   |   |   |   |   |   |   |   neighbourhood <= 30: Private room (2.0)
|   |   |   |   |   |   |   |   |   |   |   neighbourhood > 30
|   |   |   |   |   |   |   |   |   |   |   |   availability_365 <= 353: Entire home/apt (5.0)
|   |   |   |   |   |   |   |   |   |   |   |   availability_365 > 353: Private room (3.0/1.0)
|   |   |   |   |   |   |   price > 89: Entire home/apt (641.0/159.0)
|   |   |   |   price > 109: Entire home/apt (2733.0/301.0)
|   |   |   availability_365 > 361
|   |   |   |   price <= 101
|   |   |   |   |   availability_365 <= 363
|   |   |   |   |   |   neighbourhood <= 26
|   |   |   |   |   |   |   price <= 89: Entire home/apt (13.0/5.0)
|   |   |   |   |   |   |   price > 89: Private room (23.0/5.0)
|   |   |   |   |   |   neighbourhood > 26: Entire home/apt (15.0/4.0)
|   |   |   |   |   availability_365 > 363: Private room (300.0/82.0)
|   |   |   |   price > 101: Entire home/apt (565.0/193.0)
|   |   minimum_nights > 1: Entire home/apt (17317.0/1007.0)

Number of Leaves  : 	269

Size of the tree : 	537

```








<br/>


## Testing

```
Manual Testing Accuracy : 8/11 (on sample data)
Cross-Validation(10) ~90%

```

### Manual Testing Details 
- [x] 14912894,Comfy sunny double room by river,72718519,Leanne,Kingston upon Thames,51.43111701,-0.309713065,Private room,20,2,6,18-02-17,1.03,1,261 
- [ ] 17453775,Fire wood,118023775,Johan,Hounslow,51.46811131,-0.327383624,Entire home/apt,65,1,0,,,1,326
- [x] 16820619,Amazing double bedroom with en-suite,111906688,Vanessa,Brent,51.55149282,-0.217269335,Private room,30,1,0,,,1,90
- [ ] 6141967,May 6-26 Short Term Large Dble-room,10402838,Mauricio,Lambeth,51.47249468,-0.118267534,Private room,55,14,0,,,1,0
- [x] 14955982,Double room close to Central London,94147970,Anna,Southwark,51.46681835,-0.08878584,Private room,35,1,14,20-01-17,2.35,1,208
- [x] 16051798,Big double room across the river from Canary Wharf,6987866,Christina,Southwark,51.49922442,-0.034779219,Private room,32,7,1,11-02-17,1,3,239
- [x] 2559562,Room Offered,13104084,Amanda,Lewisham,51.43953488,0.002386483,Private room,25,1,4,07-11-16,0.22,1,246
- [x] 13426747,"* London Oasis - Comfortable, Stylish & Central *",3182961,Matthew,Lewisham,51.47672955,-0.023059017,Entire home/apt,75,1,2,01-11-16,0.26,3,90
- [x] 13749393,"Bright, modern top floor flat in London",10344333,David,Hammersmith and Fulham,51.50328764,-0.232923956,Entire home/apt,90,3,5,24-10-16,0.84,1,356
- [x] 9420234,Luxury and Design 4bed house Fulham,101037,"Aurelie, Alban, Eva, Sophie, Steph",Hammersmith and Fulham,51.47906719,-0.18968369,Entire home/apt,399,5,0,,,94,0

- [ ] 7327883,Comfortable king size sofa bed,2526745,Nicci,Kingston upon Thames,51.39064615,-0.310918625,Shared room,25,1,6,03-10-16,0.3,1,169

> due the traning data for Shard room in not enough, the there is a higher chances that shared property cannot be classified correctly


### The Output result sequence
```
Private home,Entire home/apt,Private home,Private home,Private home,Private home,Private home,Entire home/apt,Entire home/apt,Entire home/apt
```
