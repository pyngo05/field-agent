use field_agent;

insert into agency(agency_id, short_name, long_name) values
        (4, '13th Bureau', 'Intelligence and secret police arm of the Yellow Empire'),
        (5, 'Chapter', 'A secret organization within the US government that carries out assassinations'),
        (6, 'B613', 'B613 is a covert government agency formerly run by Rowan, later by Jake Ballard for a while and now run by Olivia Pope.'),
        (7, 'CONTROL', 'Spy agency'),
        (8, 'C.O.P.S.', 'Central Organization of Police Specialists'),
        (9, 'CURE', 'American intelligence and assassination organization'),
        (10, 'CHERUB', 'Intelligence agency that employs children'),
        (11, 'Alpha Protocol', 'A clandestine United States agency which has unlimited resources to conduct covert operations on behalf of the government');

insert into agency_agent(agency_id, agent_id, identifier, security_clearance_id, activation_date, is_active) values
        (4,1),
        (5,6 ),
        (7,3 ),
        (8,2 ),
        (9,10),
        (10,11),
        (11,5),


insert into security_clearance(security_clearance_id, name) values
        (5, 'Baseline Personnel Security Standard'),
        (6, 'Counter Terrorist Check'),
        (7, 'Security Check'),
        (8, 'Enhanced Security Check'),
        (9, 'Developed Vetting'),
        (10, 'Enhanced Developed Vetting'),
        (11, 'Developed Vetting renewal');

insert into alias(alias_id, name, persona, agent_id) values
         (1, 'Worms Outerbridge', 'Worms is a coastguard. He is very brave. His job is to rescue people.', 1),
         (2, 'Winston Tippins', 'Winston is a very chatty person. He is always on the phone to friends.', 2),
         (3, 'Ovaltine Quakenbush', 'Ovaltine is very clever. She always gets top marks in class.', 3),
         (4, 'Snakes Hoosenater', 'Snakes is a bit of a coward. He really hates going to the dentist!', 4),
         (5, 'Ignatious Hooperbag', 'Ignatious is very easy-going.', 5),
         (6, 'Squids Clovenhoof', 'Squids is really funny! She is always entertaining people with jokes and stories', 6),
         (7, 'Sid Pealike', 'Sid is really grumpy. He is not an easy person to get along with.', 7),
         (8, 'Oinks Jingley-Schmidt', 'Oinks is very hard-working. He hardly ever takes a day off.', 8);

insert into mission(mission_id, code_name, notes, start_date, projected_end_date, actual_end_date, operational_cost, agency_id) values
         (1, 'Operation Dracula', 'operation led by British and Indian forces via sea and sky', '2023-01-01', '2023-01-05', '2023-01-11', 12567899.00, 5),
         (2, 'Operation Power Geyser', 'counter-terrorism effort involving a group of 13,000 top-secret commandos', '2423-01-07', '2423-04-05', '2423-06-17', 16667899.00, 11),
         (3, 'Operation All-American Tiger', 'search and clear farms and villages to capture a handful of insurgent leaders', '2043-12-04', '2043-12-05', '2043-12-29', 12334599.00, 10),
         (4, 'Operation Beast-Master', 'uncover seven weapon caches', '2023-01-01', '2023-01-05', '2023-01-11', 55567899.00, 6),
         (5, 'Operation Mincemeat', 'lure enemies toward minefields', '2023-01-01', '2023-01-05', '2023-01-11', 12365499.00, 1),
         (6, 'Operation Beaver Cage', 'helicopter assault to capture enemies', '2023-01-01', '2023-01-05', '2023-01-11', 98767899.00, 7),
         (7, 'Operation Safe Market', 'make residential neighborhoods, marketplaces, and areas of traffic congestion safer', '2023-01-01', '2023-01-05', '2023-01-11', 12343599.00, 8),
         (8, 'Operation Magneto', 'secure an unconditional surrender of enemies', '2023-01-01', '2023-01-05', '2023-01-11', 12344899.00, 4),
         (9, 'Operation Toenails', 'major offensive to secure enemy land', '2023-01-01', '2023-01-05', '2023-01-11', 34567777.00, 9),
         (10, 'Operation Frequent Wind', 'helicopter evacuation of civilians', '2023-01-01', '2023-01-05', '2023-01-11', 12345888.00, 12),
         (11, 'Operation Lion Cub', 'Christmas donation drive', '2023-01-01', '2023-01-05', '2023-01-11', 14569999.00, 3),
         (12, 'Operation Gimlet Victory', 'counterinsurgency operation', '2023-01-01', '2023-01-05', '2023-01-11', 23422899.00, 6);

insert into mission_agent(mission_id, agent_id) values
         (1,11),
         (2,10),
         (3,9),
         (4,5),
         (5,4),
         (6,3),
         (7,2),
         (8,1);

insert into location(location_id, name, address, city, region, country_code, postal_code, agency_id) values
         (1, 'White Horse', '22 White Horse Lane', 'London', 'AL', 'UK', 'AL2 1JR', 11),
         (2, 'Avalon House', 'Flat 7, Avalon House, 260 London Road', 'St Albans', 'AL', 'UK', 'AL1 1TJ', 10),
         (3, 'Hillcrest', 'Hillcrest, Dishforth Road', 'Asenby', 'YO', 'UK', 'YO7 3QL', 9),
         (4, 'Lowfield', '27 Lowfield Drive', 'Haxby', 'YO', 'UK', 'YO32 3QT', 8),
         (5, 'Sandringham', '5 Sandringham Drive', 'Great Sankey', 'WA', 'UK', 'WA5 1JG', 7),
         (6, 'Bank House', 'Bank House, Old Market Place', 'Altrincham', 'WA', 'UK', 'WA14 4PA', 6),
         (7, 'Alverley', 'Alverley, Station Road', 'Ketley', 'TF', 'UK', 'TF1 5AQ', 5),
         (8, 'Gittens', '34 Gittens Drive', 'Telford', 'TF', 'UK', 'TF4 3SF', 4),
         (9, 'Rushendon', '48 Rushendon Furlong', 'Pitstone', 'LU', 'UK', 'LU7 9QX', 7),
         (10, 'Buckle Close', '37 Buckle Close', 'Luton', 'LU', 'UK', 'LU3 3SZ', 6),
         (11, 'Trinity Road', '90 Trinity Road', 'Luton', 'LU', 'UK', 'LU3 2LP', 4),
         (12, 'High Street', '40A High Street North', 'Dunstable', 'LU', 'UK', 'LU6 1LA', 5);


