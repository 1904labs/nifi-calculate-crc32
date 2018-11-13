# nifi-calculate-crc32
A short groovy script to generate a CRC32 hash for an eventId attribute

This script is designed to be used in an Nifi `ExecuteScript` processor.  It takes an attribute named `eventId` and computes a CRC32 checksum hash and saves it in an attribute called `hashEventId`.
