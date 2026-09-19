# OTG Recovery Assistant

Android-only safe recovery helper, version 1.

Features:
- OTG/USB device detection
- Basic USB device information
- Google Photos recovery guidance
- Safe-recovery warnings

It intentionally does NOT:
- discover, crack, guess, or bypass lock-screen PINs
- extract encrypted internal storage from a locked phone
- defeat Android security controls

## Build without a local computer

This repository is prepared for GitHub Actions. Upload the project files to a GitHub repository. The workflow in `.github/workflows/build.yml` builds a debug APK and uploads it as an artifact.

## Important

A locked Android phone may not expose its internal storage over USB. This app does not bypass that restriction.
