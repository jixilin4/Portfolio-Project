# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## [2026.03.31]

### Added

- Implemented ItinerarySecondary with secondary methods layered over kernel operations
- Implemented toString() and equals() using only kernel and Standard methods

### Changed

- Refined secondary methods to assume the one connection (two legs) itinerary case

## [2026.03.08]

### Added

- Created ItineraryKernel and Itinerary interfaces for client-side use
- Added client-side API documentation for Itinerary (kernel and enhanced)

### Changed

- Refined Itinerary design to use assemble/disassemble kernel access for safer client manipulation
- Narrowed the proof-of-concept scope to a one-connection itinerary (two legs)

## [2026.02.23]

### Added

- Created an Itinerary proof-of-concept Java file demonstrating a possible representation and key methods
- Added a simple main method demo for a one connection itinerary with baggage policy checks