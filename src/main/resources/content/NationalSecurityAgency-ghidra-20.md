#Website IPv6 support

Owner: NationalSecurityAgency

Repo: ghidra

Labels: Type: Bug 

## tambry (06 Mar 2019)

**Describe the bug**
[The official website](https://ghidra-sre.org/) isn't accessible over IPv6.

**The fix**
Since the website uses CloudFront, fixing this requires [enabling IPv6 on the CloudFront distribution](https://aws.amazon.com/de/blogs/aws/ipv6-support-update-cloudfront-waf-and-s3-transfer-acceleration/) and [creating an AAAA ALIAS record pointing to it](https://docs.aws.amazon.com/Route53/latest/DeveloperGuide/routing-to-cloudfront-distribution.html#routing-to-cloudfront-distribution-config).

## ryanmkurtz (25 Apr 2019)

Since this is the only complaint we have received so far in over a month and a half, I'm going to assume it's not a big problem and close out the ticket.  If we end up needing to make the change we'll adjust the ticket accordingly.  Thanks!

## tambry (25 Apr 2019)

@ryanmkurtz Mind you this issue has 5 thumbs ups and should take only a few minutes to fix.

## ryanmkurtz (09 Jul 2019)

IPv6 should now be enabled.

