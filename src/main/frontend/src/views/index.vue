<template>
    <div>
        <header>
            <Display msg="后台首页"></Display>
        </header>

        <main class="mt-36">
            <div class="text-center space-y-16">
                <md-icon class="rotate text-[var(--md-sys-color-tertiary)]">deployed_code</md-icon>
                <p class="italic">
                    还在开发呢
                </p>
            </div>
        </main>
    </div>
</template>

<script setup lang="ts">
import { Display } from '@/components/Text';
</script>

<style scoped>
.rotate {
    animation: rotateAnimate 4s infinite linear;
    transform: scale(6);
}
@keyframes rotateAnimate {
    from {
        transform: rotate(0deg) scale(6);
    } to {
        transform: rotate(360deg) scale(6);
    }
}
</style>